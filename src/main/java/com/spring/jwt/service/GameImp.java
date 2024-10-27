package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IGame;
import com.spring.jwt.dto.NumberDto;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseSizeObjectDto;
import com.spring.jwt.entity.ChartTrend;
import com.spring.jwt.entity.GameColorNumber;
import com.spring.jwt.entity.User;
import com.spring.jwt.entity.WinNumber;
import com.spring.jwt.repository.ChartTrendRepo;
import com.spring.jwt.repository.GameColorNumberRepo;
import com.spring.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime localDateTime = LocalDateTime.now();

        String pattern = "yyyyMMddHHmm"; // You can change this pattern as needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = localDateTime.format(formatter);


            Long i = Long.valueOf(formattedDateTime);
            ChartTrend chartTrend = ChartTrend.builder()
                    .runningStatus("_RUNNING_")
                    .period(i)
                    .wonNumber(-1)
                    .wonColor(-1)
                    .dateTime(LocalDateTime.now())
                    .build();

            chartTrendRepo.save(chartTrend);

        return "saved chartTrend";
    }



    @Override
    public String updateChartTrend(Integer wonNumber,Integer wonColor) {

        try {

            ChartTrend chartTrend = chartTrendRepo.findByRunningStatus("_RUNNING_").orElseThrow(() -> new RuntimeException("chart treand details not found by id"));
            chartTrend.setRunningStatus("_DONE_");
            chartTrend.setWonNumber(wonNumber);
            chartTrend.setWonColor(wonColor);


            chartTrendRepo.save(chartTrend);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        LocalDateTime localDateTime = LocalDateTime.now();

        String pattern = "yyyyMMddHHmm"; // You can change this pattern as needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = localDateTime.format(formatter);

        ChartTrend chartTrendNew = ChartTrend.builder()
                .runningStatus("_RUNNING_")
                .period(Long.valueOf(formattedDateTime))
                .wonNumber(-1)
                .wonColor(-1)
                .dateTime(LocalDateTime.now())
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
        System.out.println(nine);
        System.out.println(black +" "+ red +" "+yellow +" "+ zero +" "+one +" "+ two +" "+three +" "+ four +" "+five +" "+ six +" "+ seven+" "+ eight +" "+nine);


        List<Integer> listOfNumber = new ArrayList<>();
        List<Integer> listOfColor = new ArrayList<>();
        List<NumberDto> listOfNumbers = new ArrayList<>();
        List<NumberDto> listOfColors = new ArrayList<>();
        NumberDto numberDto;



//      _YELLOW_  == 101
//        _RED_   == 102
//        _BLACK_ == 103
        if (yellow!=null) {
            listOfColor.add(yellow);
            numberDto = new NumberDto(yellow,101);
            listOfColors.add(numberDto);


        }
        if (red!=null) {
            listOfColor.add(red);

            numberDto = new NumberDto(red,102);

            listOfColors.add(numberDto);



        }
        if (black!=null) {
            listOfColor.add(black);

            numberDto = new NumberDto(black,103);

            listOfColors.add(numberDto);



        }


        //numbers//
        if (zero!=null) {
            listOfNumber.add(zero);

            numberDto = new NumberDto(zero,0);

            listOfNumbers.add(numberDto);
        }
        if (five!=null) {
            listOfNumber.add(five);

            numberDto = new NumberDto(five,5);
            listOfNumbers.add(numberDto);        }
        if (one!=null) {
            listOfNumber.add(one);

            numberDto = new NumberDto(one,1);

            listOfNumbers.add(numberDto);
        }
        if (two!=null) {
            listOfNumber.add(two);

            numberDto = new NumberDto(two,2);

            listOfNumbers.add(numberDto);        }
        if (three!=null) {
            listOfNumber.add(three);

            numberDto = new NumberDto(three,3);

            listOfNumbers.add(numberDto);        }
        if (four!=null) {
            listOfNumber.add(four);

            numberDto = new NumberDto(four,4);

            listOfNumbers.add(numberDto);        }

        if (six!=null) {
            listOfNumber.add(six);

            numberDto = new NumberDto(six,6);
            listOfNumbers.add(numberDto);        }
        if (seven!=null) {
            listOfNumber.add(seven);

            numberDto = new NumberDto(seven,7);
            listOfNumbers.add(numberDto);        }
        if (eight!=null) {
            listOfNumber.add(eight);

            numberDto = new NumberDto(eight,8);
            listOfNumbers.add(numberDto);        }
        if (nine!=null) {
            listOfNumber.add(nine);

            numberDto = new NumberDto(nine,9);
            listOfNumbers.add(numberDto);
        }
        System.out.println(listOfNumber);
        System.out.println(listOfColor);

//        Collections.sort(listOfNumber);
        Collections.sort(listOfColor);
//
        System.out.println(listOfNumber);
        System.out.println(listOfColor);

        for(NumberDto i :listOfNumbers){
            System.out.print(i.toString());
        }



//        Integer numberResult = getResultNumber(listOfNumber,listOfNumbers);
//        Integer colorResult = getResultcolor(listOfColor,listOfColors);
        Integer numberResult = 1;
        Integer colorResult = 101;
//        List<User> users =
        System.out.println(numberResult + " "+colorResult);
        updateChartTrend(numberResult,colorResult);
        return String.valueOf(0);

    }

    private Integer getResultcolor(List<Integer> listOfColor,List<NumberDto> listOfColors) {
        Integer finalWonNumber = -1;
        for (NumberDto numberDto : listOfColors){
            System.out.println(listOfColor.get(0)+ " "+numberDto.key);
            if (listOfColor.get(0) == numberDto.key){
                finalWonNumber = numberDto.value;
                break;
            }
        }

        return finalWonNumber;

    }

    private Integer getResultNumber(List<Integer> listOfNumber,List<NumberDto> listOfNumbers) {
//        Integer ZeroOrFiveResultNumber = getResultforFiveZeroNumber(listOfNumber);
        Integer finalWonValue = -1;

        Integer sumOfAllNumber = 0;
        List<Integer> byteList = new LinkedList<>();
        for (Integer i : listOfNumber){sumOfAllNumber = sumOfAllNumber+i;}
//        Integer twentyPersent = (int) ((0.20) * sumOfAllNumber);
//        Integer thirtyPersent = (int) ((0.30) * sumOfAllNumber);
//        Integer FourtyPersent = (int) ((0.40) * sumOfAllNumber);
//        Integer fiftyPersent  = (int) ((0.50) * sumOfAllNumber);
        Integer fiveNumberValueAllTotal = 1;
        Integer zeroNumberValueAllTotal = 0;
        for (Integer i = 0; i<10;i++){


            if ((listOfNumbers.get(zeroNumberValueAllTotal).key != 0 && listOfNumbers.get(zeroNumberValueAllTotal).value != listOfNumber.get(i))&&
                    (listOfNumbers.get(fiveNumberValueAllTotal).key != 5 && listOfNumbers.get(fiveNumberValueAllTotal).value != listOfNumber.get(i))){

                Integer allNineMultValue = (listOfNumber.get(i))+((listOfNumber.get(i))*9);

                Integer sumOfOtherNineNumber =0;

                for (Integer j = 0; j<10;j++) {
                    if (i!=j){
                        sumOfOtherNineNumber = sumOfOtherNineNumber +listOfNumber.get(i);
                    }else if (j == i){
                        System.out.println("im inside i == j");
                    }
                }
                Integer fiftyPersent = (int) ((0.50) * sumOfOtherNineNumber);
                Integer seventyFivePersent = (int) ((0.75) * sumOfOtherNineNumber);
                System.out.println(fiftyPersent +"  "+seventyFivePersent );
                System.err.println(allNineMultValue +"all sum"+ sumOfOtherNineNumber);
                System.err.println((allNineMultValue >= fiftyPersent) && (allNineMultValue <= seventyFivePersent));
                if (allNineMultValue < sumOfOtherNineNumber && (allNineMultValue >= fiftyPersent) && (allNineMultValue <= seventyFivePersent)){
                    finalWonValue =  getMyWonNumberbyNus(listOfNumbers,listOfNumber.get(i));
                } else if (allNineMultValue <= fiftyPersent) {
                    byteList.add(i);
                }
                System.out.println("i value is :"+i);
            }
            if (finalWonValue == -1){
                finalWonValue = getResultforFiveZeroNumber(zeroNumberValueAllTotal,fiveNumberValueAllTotal,listOfNumbers,byteList,listOfNumber);
            }


        }



        return finalWonValue;

    }

    private Integer getMyWonNumberbyNus(List<NumberDto> listOfNumbers, Integer key) {
        Integer finalWonNumber = -1;
        for (NumberDto numberDto : listOfNumbers){
            if (numberDto.key == key){
                finalWonNumber = numberDto.value;
            }
        }
        return finalWonNumber;
    }

    private Integer getResultforFiveZeroNumber(Integer zeroNumberValueAllTotal,
                                               Integer fiveNumberValueAllTotal,
                                               List<NumberDto> listOfNumbers,
                                               List<Integer> byteList,
                                               List<Integer> listOfNumber) {

        Integer finalWonValue = -1;
        Integer zeroNumberValue = 0;

        Integer fiveNumberValue = 0;
        for (NumberDto numberDto : listOfNumbers){
            if (numberDto.key == zeroNumberValue){
                zeroNumberValue = numberDto.value;
            }else if (numberDto.key == fiveNumberValue){
                fiveNumberValue = numberDto.value;
            }else if (zeroNumberValue > 0 &&fiveNumberValue > 0 ){
                break;
            }
        }

        for (Integer postionNumbers =0;postionNumbers<byteList.size();postionNumbers++){
            Integer postionNum = listOfNumber.get(byteList.get(postionNumbers));
            if (postionNum != zeroNumberValue  && postionNum!= fiveNumberValue && postionNum > zeroNumberValue  && postionNum > fiveNumberValue ){
                finalWonValue = getMyWonNumberbyNus(listOfNumbers,postionNum);
                return finalWonValue;
            }
        }
        if (zeroNumberValue == fiveNumberValue){
            Random random = new Random();

            Integer randomValue = random.nextInt(2);
            System.out.println("random number is : "+randomValue);
            if (randomValue == 0){
                finalWonValue = 0;
            }else {
                finalWonValue = 5;
            }
        }

        return finalWonValue;

    }

}
