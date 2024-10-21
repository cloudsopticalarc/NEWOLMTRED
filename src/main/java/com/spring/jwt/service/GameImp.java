package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IGame;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseSizeObjectDto;
import com.spring.jwt.entity.ChartTrend;
import com.spring.jwt.entity.GameColorNumber;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.ChartTrendRepo;
import com.spring.jwt.repository.GameColorNumberRepo;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameImp implements IGame {
    @Autowired
    private GameColorNumberRepo gameColorNumberRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChartTrendRepo chartTrendRepo;
    @Override
    public String saveGameColorOrNumber(String referenceId, String colorOrNumber,Integer amount,String period)throws RuntimeException {
        User user = userRepository.findByReferenceId(referenceId).orElseThrow(()->new RuntimeException("user reference invalid"));
        if (colorOrNumber.isEmpty())throw new RuntimeException("Enter valid color or number");
        GameColorNumber gameColorNumber = GameColorNumber
                .builder()
                    .wonNumber(-1)
                        .dateAndTime(LocalDateTime.now())
                            .amount(amount)
                                .red(false)
                                     .black(false)
                                         .yellow(false)
                                              .zero(false)
                                                   .one(false)
                                                       .two(false)
                                                          .three(false)
                                                            .four(false)
                                                                 .five(false)
                                                                      .six(false)
                                                                        .seven(false)
                                                                            .eight(false)
                                                                                .nine(false)
                                                                                    .period(period)
                                                                                        .userReferenceId(referenceId)
                                                                                            .winStatus(false)
                        .build();


        if (colorOrNumber.equals("_ONE_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setOne(true);

        } else if (colorOrNumber.equals("_TWO_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setTwo(true);

        }else if (colorOrNumber.equals("_THREE_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setThree(true);

        }
        else if (colorOrNumber.equals("_FOUR_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setFour(true);

        }else if (colorOrNumber.equals("_FIVE_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setFive(true);

        }else if (colorOrNumber.equals("_SIX_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setSix(true);

        }else if (colorOrNumber.equals("_SEVEN_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setSeven(true);

        }else if (colorOrNumber.equals("_EIGHT_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setEight(true);

        }else if (colorOrNumber.equals("_NINE_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setNine(true);

        }else if (colorOrNumber.equals("_ZERO_")){
            gameColorNumber.setType("_NUMBER_");
            gameColorNumber.setOne(true);

        }else if (colorOrNumber.equals("_RED_")){
            gameColorNumber.setType("_COLOR_");
            gameColorNumber.setRed(true);

        }else if (colorOrNumber.equals("_BLACK_")){
            gameColorNumber.setType("_COLOR_");
            gameColorNumber.setBlack(true);

        }else if (colorOrNumber.equals("_YELLOW_")){
            gameColorNumber.setType("_COLOR_");
            gameColorNumber.setYellow(true);

        }else {
            throw new RuntimeException("type or color or number is not valid input");
        }
        if (user.getTotalBalnce()<=0){
            throw new RuntimeException("low balance");
        } else if (user.getTotalBalnce() < amount) {
            throw new RuntimeException("invalid amount");
        }
        user.setTotalBalnce((user.getTotalBalnce())-amount);
        gameColorNumberRepo.save(gameColorNumber);
        return "request saved";


    }

    @Override
    public String saveChartTrend() {

        List<ChartTrend> listOFChartTrend = chartTrendRepo.findAll();
        if(listOFChartTrend.size() == 0){
            Long i = 2000000000000L;
            ChartTrend chartTrend = ChartTrend.builder()
                    .runningStatus("_RUNNING_")
                    .period(i)
                    .wonNumber(-1)
                    .build();
            chartTrendRepo.save(chartTrend);
        }else {

            Long i = (2000000000000L+listOFChartTrend.size())+1;


            ChartTrend chartTrend = ChartTrend.builder()
                    .runningStatus("_RUNNING_")
                    .period(i)
                    .wonNumber(-1)
                    .build();
            chartTrendRepo.save(chartTrend);
        }
        return "saved chartTrend";
    }



    @Override
    public String updateChartTrend(Integer wonNumber) {

        ChartTrend chartTrend = chartTrendRepo.findByRunningStatus("_RUNNING_").orElseThrow(()->new RuntimeException("chart treand details not found by id"));
        chartTrend.setRunningStatus("_DONE_");
        chartTrend.setWonNumber(wonNumber);
        chartTrendRepo.save(chartTrend);



        ChartTrend chartTrendNew = ChartTrend.builder()
                .runningStatus("_RUNNING_")
                .period(chartTrend.getPeriod()+1)
                .wonNumber(-1)
                .build();
        chartTrendRepo.save(chartTrendNew);
        return "updated Chart Trend";

    }

    @Override
    public Object getLivePeriodNo() {
        List<ChartTrend> listOFChartTrend = chartTrendRepo.findAll();
        Long size = (long) listOFChartTrend.size();
        return new ResponseSizeObjectDto(size,listOFChartTrend);

    }

    @Override
    public String makeWinNumber() {
            Integer black = gameColorNumberRepo.finByBlack(true);
            Integer red =gameColorNumberRepo.findByRed(true);
            Integer yellow =gameColorNumberRepo.findByYellow(true);
            Integer zero =gameColorNumberRepo.findByZero(true);
            Integer one =gameColorNumberRepo.findByOne(true);
            Integer two =gameColorNumberRepo.findByTwo(true);
            Integer three =gameColorNumberRepo.findByThree(true);
            Integer four =gameColorNumberRepo.findByFour(true);
            Integer five =gameColorNumberRepo.findByFive(true);
            Integer six =gameColorNumberRepo.findBySix(true);
            Integer seven =gameColorNumberRepo.findBySeven(true);
            Integer eight =gameColorNumberRepo.findByEight(true);
            Integer nine =gameColorNumberRepo.findByNine(true);
            Integer finalWonValue = -1;

            System.out.println(nine);
            System.out.println(black +" "+ red +" "+yellow +" "+ zero +" "+one +" "+ two +" "+three +" "+ four +" "+five +" "+ six +" "+ seven+" "+ eight +" "+nine);

            List<Integer> listOfNumber = new ArrayList<>();
            Map<Integer,Integer> mapOfNumbers = new HashMap<>();
            List<Integer> listOfColor = new ArrayList<>();
            Map<Integer,String> mapOfColor = new HashMap<>();

            if (yellow!=null) {
                listOfColor.add(yellow);
                mapOfColor.put(yellow,"_YELLOW_");
            };
            if (red!=null) {
                listOfColor.add(red);
                mapOfColor.put(red,"_RED_");

            };
            if (black!=null) {
                listOfColor.add(black);
                mapOfColor.put(black,"_BLACK_");

            };
            if (zero!=null) {
                listOfNumber.add(zero);
                mapOfNumbers.put(zero,0);
            };
            if (one!=null) {
                listOfNumber.add(one);
                mapOfNumbers.put(one,1);
            };
            if (two!=null) {
                listOfNumber.add(two);
                mapOfNumbers.put(two,2);
            };
            if (three!=null) {
                listOfNumber.add(three);
                mapOfNumbers.put(three,3);
            };
            if (four!=null) {
                listOfNumber.add(four);
                mapOfNumbers.put(four,4);
            };
            if (five!=null) {
                listOfNumber.add(five);
                mapOfNumbers.put(five,5);
            };
            if (six!=null) {
                listOfNumber.add(six);
                mapOfNumbers.put(six,6);
            };
            if (seven!=null) {
                listOfNumber.add(seven);
                mapOfNumbers.put(seven,7);
            };
            if (eight!=null) {
                listOfNumber.add(eight);
                mapOfNumbers.put(eight,8);
            };
            if (nine!=null) {
                listOfNumber.add(nine);
                mapOfNumbers.put(nine,9);
            };
            System.out.println(listOfNumber);
            System.out.println(listOfColor);

            Collections.sort(listOfNumber);
            Collections.sort(listOfColor);

            System.out.println(listOfNumber);
            System.out.println(listOfColor);


            System.out.println(listOfNumber.size());
            Boolean selectZeroOrFiveNumber = false;
            for (Integer i =0;i<listOfNumber.size()-1;i++){
                if (listOfNumber.get(i).equals(listOfNumber.get(i+1))){
                    selectZeroOrFiveNumber = true;
                }else {
                    selectZeroOrFiveNumber = false;
                }
            }
            if (selectZeroOrFiveNumber){
                Random random = new Random();

                Integer randomValue = random.nextInt(2);
                if (randomValue == 0){
                    finalWonValue = 0;
                }else {
                    finalWonValue = 5;
                }
                return String.valueOf(finalWonValue);
            }

            Integer result = getResultNumber(listOfNumber,listOfColor);





            return String.valueOf(0);

    }

    private Integer getResultNumber(List<Integer> listOfNumber, List<Integer> lisOfColor) {
      Integer sumOfAllNumber = 0;
            for (Integer i : listOfNumber){sumOfAllNumber = sumOfAllNumber+i;}
            Integer twentyPersent = (int) ((0.20) * sumOfAllNumber);
            Integer thirtyPersent = (int) ((0.30) * sumOfAllNumber);
            Integer FourtyPersent = (int) ((0.40) * sumOfAllNumber);
            Integer fiftyPersent  = (int) ((0.50) * sumOfAllNumber);

//
//        for (Integer i = )
//        for ()
        return 0;

    }

}
