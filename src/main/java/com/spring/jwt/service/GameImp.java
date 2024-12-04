package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IGame;
import com.spring.jwt.dto.NumberDto;
import com.spring.jwt.dto.ResponceDto;
import com.spring.jwt.dto.ResponseSizeObjectDto;
import com.spring.jwt.entity.*;
import com.spring.jwt.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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
    private ProfitGameRepo profitRepo;
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
                                                                                                .userId(user.getId())
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
            gameColorNumber.setZero(true);

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

            List<ChartTrend> chartTrend = chartTrendRepo.findByRunningStatus("_RUNNING_");
            Integer size = chartTrend.size();
            if (size<1){
                throw new RuntimeException("chart treand details not found by id");
            }


            chartTrend.get(size-1).setRunningStatus("_DONE_");
            chartTrend.get(size-1).setWonNumber(wonNumber);
            chartTrend.get(size-1).setWonColor(wonColor);


            chartTrendRepo.save(chartTrend.get(size-1));
        }catch (RuntimeException e){
             System.err.println(e.getMessage());
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
    public ChartTrend getLivePeriodNo() {

        try {

            List<ChartTrend> chartTrend = chartTrendRepo.findByRunningStatus("_RUNNING_");
            Integer size = chartTrend.size();
            if (size<1){
                throw new RuntimeException("chart treand details not found by id");
            }


            return chartTrend.get(size-1);
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
        }

        List<ChartTrend> listOFChartTrend = chartTrendRepo.findAll();
        return listOFChartTrend.get(listOFChartTrend.size()-1);
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

        Boolean flag = true;
        // // System.out.println(nine);
        Integer numberResult = -1;
        Integer colorResult = -1;
        //
        if(zero==null &&
         one==null &&
                two==null &&
                three ==null && four==null &&
                five==null && six==null &&
                seven==null && eight==null &&
                nine==null && black==null &&
                yellow==null && red==null )
        {
            Random random = new Random();

            Integer randomValue = random.nextInt(9);
            // // System.out.println("random number is : "+randomValue);
            numberResult = randomValue;
            randomValue = random.nextInt(3);
            if (randomValue == 0){
                colorResult = 101;
            }else if (randomValue == 1){
                colorResult = 102;
            }else {
                colorResult = 103;
            }
            System.err.println(colorResult +" "+numberResult);
            ProfitGame profit = ProfitGame.builder()
                    .TransactionsDateAndTime(LocalDateTime.now())
                    .totalAmountColor(0)
                    .totalAmountNumber(0)
                    .profitAmountColor(0)
                    .profitAmountNumber(0)
                    .period(String.valueOf(getLivePeriodNo().getPeriod()))
                    .status("SUCCESS")
                    .sourceOfProfit("GAME")
                    .build();

            profitRepo.save(profit);

            updateChartTrend(numberResult,colorResult);
             return colorResult +" "+numberResult;
        }

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


        }else {
            listOfColor.add(0);

            numberDto = new NumberDto(0,101);

            listOfColors.add(numberDto);



        }
        if (red!=null) {
            listOfColor.add(red);

            numberDto = new NumberDto(red,102);

            listOfColors.add(numberDto);



        }else {
            listOfColor.add(0);

            numberDto = new NumberDto(0,102);

            listOfColors.add(numberDto);



        }
        if (black!=null) {
            listOfColor.add(black);

            numberDto = new NumberDto(black,103);

            listOfColors.add(numberDto);



        }else {
            listOfColor.add(0);

            numberDto = new NumberDto(0,103);

            listOfColors.add(numberDto);



        }


        //numbers//
        if (zero!=null) {
            listOfNumber.add(zero);

            numberDto = new NumberDto(zero,0);

            listOfNumbers.add(numberDto);
        }else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,0);

            listOfNumbers.add(numberDto);



        }
        if (five!=null) {
            listOfNumber.add(five);

            numberDto = new NumberDto(five,5);
            listOfNumbers.add(numberDto);        }
        else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,5);

            listOfNumbers.add(numberDto);



        }
        if (one!=null) {
            listOfNumber.add(one);

            numberDto = new NumberDto(one,1);

            listOfNumbers.add(numberDto);
        }else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,1);

            listOfNumbers.add(numberDto);



        }
        if (two!=null) {
            listOfNumber.add(two);

            numberDto = new NumberDto(two,2);

            listOfNumbers.add(numberDto);        }
        else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,2);

            listOfNumbers.add(numberDto);



        }if (three!=null) {
            listOfNumber.add(three);

            numberDto = new NumberDto(three,3);

            listOfNumbers.add(numberDto);        }
        else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,3);

            listOfNumbers.add(numberDto);



        }if (four!=null) {
            listOfNumber.add(four);

            numberDto = new NumberDto(four,4);

            listOfNumbers.add(numberDto);        }

        else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,4);

            listOfNumbers.add(numberDto);



        }        if (six!=null) {
            listOfNumber.add(six);

            numberDto = new NumberDto(six,6);
            listOfNumbers.add(numberDto);
        }else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,6);

            listOfNumbers.add(numberDto);



        }
        if (seven!=null) {
            listOfNumber.add(seven);

            numberDto = new NumberDto(seven,7);
            listOfNumbers.add(numberDto);        }
        else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,7);

            listOfNumbers.add(numberDto);



        }
        if (eight!=null) {
            listOfNumber.add(eight);

            numberDto = new NumberDto(eight,8);
            listOfNumbers.add(numberDto);        }
        else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,8);

            listOfNumbers.add(numberDto);



        }
        if (nine!=null) {
            listOfNumber.add(nine);

            numberDto = new NumberDto(nine,9);
            listOfNumbers.add(numberDto);
        }else {
            listOfNumber.add(0);

            numberDto = new NumberDto(0,9);

            listOfNumbers.add(numberDto);



        }
        System.out.println(black +" "+ red +" "+yellow +" "+ zero +" "+one +" "+ two +" "+three +" "+ four +" "+five +" "+ six +" "+ seven+" "+ eight +" "+nine);

          System.out.println(listOfNumber);
          System.out.println(listOfColor);

//        Collections.sort(listOfNumber);
        Collections.sort(listOfColor);
        System.out.println(listOfColor);
//
        // // System.out.println(listOfNumber);
        // // System.out.println(listOfColor);
//
//        for(NumberDto i :listOfNumbers){
//            // System.out.println(i.toString());
//        }
//
//        // System.out.println(listOfNumbers.get(7).key);
//        // System.out.println(listOfNumbers.get(8).key);
//        // System.out.println(listOfNumbers.get(9).key);

         numberResult = getResultNumber(listOfNumber,listOfNumbers);
         colorResult = getResultcolor(listOfColor,listOfColors);
        // System.out.println("Final :"+numberResult + " "+colorResult);

        // System.out.println("460");
        Integer sumOfNum =sumOfTotalAmount(listOfNumbers,"NUMBER");
        // System.out.println("460");

        Integer sumOfColor=sumOfTotalAmount(listOfColors,"COLOR");
        // System.out.println("460");

        Integer sumOfNumP =sumOfProfit(listOfNumbers,numberResult);
        // System.out.println("460");

        Integer sumOfColorP=sumOfProfitcolor(listOfColors,colorResult);
        // System.out.println("460");
        // System.out.println(sumOfNum+" "+sumOfColor );
//
//

        saveNumberWonUserAmount(numberResult,colorResult);
         System.out.println("534");
                    ProfitGame profit = ProfitGame.builder()
                            .TransactionsDateAndTime(LocalDateTime.now())
                            .totalAmountColor(sumOfColor)
                            .totalAmountNumber(sumOfNum)
                            .profitAmountColor(sumOfColor-sumOfColorP)
                            .profitAmountNumber(sumOfNum-sumOfNumP)
                            .period(String.valueOf(getLivePeriodNo().getPeriod()))
                            .status("SUCCESS")
                            .sourceOfProfit("GAME")
                            .build();

            profitRepo.save(profit);
        System.err.println(colorResult +" "+numberResult +"result without condition");

            updateChartTrend(numberResult,colorResult);

        return colorResult +" "+numberResult;
//        return String.valueOf(colorResult);
    }
    private Integer getResultcolor(List<Integer> listOfColor,List<NumberDto> listOfColors) {
        Integer finalWonNumber = -1;
        for (NumberDto numberDto : listOfColors){

            // // System.out.println(listOfColor.get(0)+ " "+numberDto.key);
            if (numberDto.key == listOfColor.get(1)){
                finalWonNumber = numberDto.value;
                break;
            }
        }

        return finalWonNumber;

    }
    private Integer sumOfTotalAmount(List<NumberDto> list,String type) {
        Integer sumOfTotalAmount = 0;
        for (NumberDto numberDto : list){

//             // System.out.println(numberDto.value+ " "+numberDto.key);

                sumOfTotalAmount =sumOfTotalAmount +(numberDto.key);


        }
//        // System.out.println(sumOfTotalAmount);
        return sumOfTotalAmount;

    }


    private Integer sumOfProfit(List<NumberDto> list, Integer winNum) {
        Integer sumOfTotalAmount = 0;
        for (NumberDto numberDto : list){
            // // System.out.println(listOfColor.get(0)+ " "+numberDto.key);
            // System.out.println("inside sumOfProfit"+winNum +" "+numberDto.value +" "+numberDto.key+" "+(winNum==numberDto.value));
            if (winNum == numberDto.value){
                sumOfTotalAmount =(numberDto.key);
                break;
            }
        }
        // System.out.println(sumOfTotalAmount);
        sumOfTotalAmount = (int) ((sumOfTotalAmount) + (sumOfTotalAmount * 8.5f));
        // System.out.println(sumOfTotalAmount);
        return sumOfTotalAmount;



    }
    private Integer sumOfProfitcolor(List<NumberDto> list, Integer winNum) {
        Integer sumOfTotalAmount = 0;
        for (NumberDto numberDto : list){
            // // System.out.println(listOfColor.get(0)+ " "+numberDto.key);
            // System.out.println("inside sumOfProfit"+winNum +" "+numberDto.value +" "+numberDto.key+" "+(winNum==numberDto.value));
            if (winNum == numberDto.value){
                sumOfTotalAmount =(numberDto.key);
                break;
            }
        }
        // System.out.println(sumOfTotalAmount);
        sumOfTotalAmount = (int) ((sumOfTotalAmount) + (sumOfTotalAmount * 0.9));
        // System.out.println(sumOfTotalAmount);
        return sumOfTotalAmount;



    }
    private Integer getResultNumber(List<Integer> listOfNumber,List<NumberDto> listOfNumbers) {

        Integer finalWonValue = -1;

        if(listOfNumbers.get(2).key==(listOfNumbers.get(1).key * 0.5) &&
                listOfNumbers.get(2).key==(listOfNumbers.get(0).key * 0.5) &&
                listOfNumbers.get(2).key==listOfNumbers.get(3).key &&
                listOfNumbers.get(2).key==listOfNumbers.get(4).key &&
                listOfNumbers.get(2).key==listOfNumbers.get(5).key &&
                listOfNumbers.get(2).key==listOfNumbers.get(6).key &&
                listOfNumbers.get(2).key==listOfNumbers.get(7).key &&
                listOfNumbers.get(2).key==listOfNumbers.get(8).key){
            Random random = new Random();

            Integer randomValue = random.nextInt(9);
        return randomValue;
        }

        Integer sumOfAllNumber = 0;
        List<Integer> byteList = new LinkedList<>();
        for (Integer i : listOfNumber){sumOfAllNumber = sumOfAllNumber+i;}
//        Integer seventyFive = (int) ((0.75) * sumOfAllNumber);
        Integer fiveNumberValueAllTotal = 1;
        Integer zeroNumberValueAllTotal = 0;
        for (Integer i = 2; i<listOfNumbers.size();i++) {
            Integer allNineMultValue = (listOfNumbers.get(i)).key*10;
            Integer sumOfOtherNineNumber =0;
            for (Integer j = 0; j<listOfNumbers.size();j++) {
                if (j!=i) {sumOfOtherNineNumber = sumOfOtherNineNumber + listOfNumbers.get(j).key;}
            }
            Integer seventyFive = (int) ((0.75) * sumOfOtherNineNumber);

            if (allNineMultValue <= seventyFive ){
                finalWonValue = listOfNumbers.get(i).value;
            }
        }
        if (finalWonValue == -1){

            for (Integer i = 0; i<2;i++) {
                Integer allNineMultValue = (int) (listOfNumbers.get(i).key * 0.5) *10;
                Integer sumOfOtherNineNumber =0;
                for (Integer j = 0; j<listOfNumbers.size();j++) {
                    if (j!=i) {sumOfOtherNineNumber = sumOfOtherNineNumber + listOfNumbers.get(j).key;}
                }
                Integer seventyFive = (int) ((0.75) * sumOfOtherNineNumber);

                if (allNineMultValue <= seventyFive ){
                    finalWonValue = listOfNumbers.get(i).value;
                }
            }
        }
        return finalWonValue;





//        Integer ZeroOrFiveResultNumber = getResultforFiveZeroNumber(listOfNumber);
//        Integer finalWonValue = -1;
//
//        Integer sumOfAllNumber = 0;
//        List<Integer> byteList = new LinkedList<>();
//        for (Integer i : listOfNumber){sumOfAllNumber = sumOfAllNumber+i;}
////        // System.out.println("423");
////        Integer twentyPersent = (int) ((0.20) * sumOfAllNumber);
////        Integer thirtyPersent = (int) ((0.30) * sumOfAllNumber);
////        Integer FourtyPersent = (int) ((0.40) * sumOfAllNumber);
////        Integer fiftyPersent  = (int) ((0.50) * sumOfAllNumber);
//        Integer fiveNumberValueAllTotal = 1;
//        Integer zeroNumberValueAllTotal = 0;
//        for (Integer i = 2; i<listOfNumbers.size();i++) {
//            Boolean stopLoopFlag = false;
//
////            if ((listOfNumbers.get(i).value != 0 && listOfNumbers.get(zeroNumberValueAllTotal).key != listOfNumber.get(i)) &&
////                    (listOfNumbers.get(i).value != 5 && listOfNumbers.get(fiveNumberValueAllTotal).key != listOfNumber.get(i))) {
////                // // System.out.println("ok");
////                }
////
////        else{
////                // // System.out.println("not ok");
////            }
////            if ((listOfNumbers.get(i).value != 0 && listOfNumbers.get(zeroNumberValueAllTotal).key != listOfNumber.get(i)) &&
////                    (listOfNumbers.get(i).value != 5 && listOfNumbers.get(fiveNumberValueAllTotal).key != listOfNumber.get(i))) {
//            // System.out.println(listOfNumbers.size()+" "+i);
//
//            // System.out.println("444");
////                // System.err.println(allNineMultValue +" "+(listOfNumber.get(i))*10);
//            Integer sumOfOtherNineNumber =0;
//
//            for (Integer j = 2; j<listOfNumbers.size();j++) {
//
//                if (i!=j && (listOfNumbers.get(j).value != 0 ) &&
//                        (listOfNumbers.get(j).value != 5 )){
//                    sumOfOtherNineNumber = sumOfOtherNineNumber +listOfNumber.get(j);
//                    // System.out.println(sumOfOtherNineNumber+" " +listOfNumber.get(j)+" "+j);
//                }else if (j == i){
//                    // System.out.println("im inside i == j");
//                }
//            }
//
//            sumOfOtherNineNumber=sumOfOtherNineNumber+listOfNumbers.get(zeroNumberValueAllTotal).key+listOfNumbers.get(fiveNumberValueAllTotal).key;
//            // System.err.println(sumOfOtherNineNumber +" "+allNineMultValue);
//
//
//            Integer fiftyPersent = (int) ((0.50) * sumOfOtherNineNumber);
//            Integer seventyFivePersent = (int) ((0.75) * sumOfOtherNineNumber);
////                 // System.out.println(fiftyPersent +"  "+seventyFivePersent );
////                // System.err.println(allNineMultValue +"all sum"+ sumOfOtherNineNumber);
////                // System.err.println((allNineMultValue >= fiftyPersent) && (allNineMultValue <= seventyFivePersent));
//            // System.err.println( (allNineMultValue < sumOfOtherNineNumber && ((allNineMultValue >= fiftyPersent) && (allNineMultValue <= seventyFivePersent))));
//
//            if (allNineMultValue < sumOfOtherNineNumber && ((allNineMultValue >= fiftyPersent) && (allNineMultValue <= seventyFivePersent))){
//                finalWonValue =  getMyWonNumberbyNus(listOfNumbers,listOfNumber.get(i));
//                stopLoopFlag = true;
//                break;
//            } else if (allNineMultValue > 0 && allNineMultValue <= fiftyPersent) {
//                byteList.add(i);
//
//            }
//            if (stopLoopFlag)break;
////                 // System.out.println(allNineMultValue +" "+ fiftyPersent);
////            }
//            // System.out.println("byte      "+byteList.size());
//
//
//
//        }
//        if (finalWonValue == -1){
//            finalWonValue = getResultforFiveZeroNumber(zeroNumberValueAllTotal,fiveNumberValueAllTotal,listOfNumbers,byteList,listOfNumber);
//        }
//
//
//
//        return finalWonValue;

    }
    private Integer getMyWonNumberbyNus( List<NumberDto> listOfNumbers,Integer key) {
        Integer finalWonNumber = -3;


        for (NumberDto numberDto : listOfNumbers){
            // System.err.println(key);
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
        if (listOfNumbers.size()<=0){
            return -1;
        }

        Integer finalWonValue = -2;
        Integer zeroNumberValue = listOfNumbers.get(0).key;

        Integer fiveNumberValue = listOfNumbers.get(1).key;
        List<Integer> list = new LinkedList<>();
        for (Integer postion : byteList){
            list.add(listOfNumbers.get(postion).key);
        }
        Collections.sort(list);
//                for (Integer postionNumbers =0;postionNumbers<byteList.size();postionNumbers++){
//                    Integer postionNum = listOfNumber.get(byteList.get(postionNumbers));
//                    if (postionNum != zeroNumberValue  && postionNum!= fiveNumberValue ){
//                        finalWonValue = getMyWonNumberbyNus(listOfNumbers,postionNum);
//                        return finalWonValue;
//                    }
//                }
        if (!byteList.isEmpty() && (list.get(list.size() - 1) >= zeroNumberValue) && (list.get(list.size() - 1) >= fiveNumberValue)) {
            finalWonValue = getMyWonNumberbyNus(listOfNumbers, list.get(list.size() - 1));
            return finalWonValue;
        }
        if (!byteList.isEmpty() && (list.get(list.size() - 1) != zeroNumberValue) && (list.get(list.size() - 1) != fiveNumberValue)) {
            finalWonValue = getMyWonNumberbyNus(listOfNumbers, list.get(list.size() - 1));
            return finalWonValue;
        }

        Integer sumOfAll = 0;
        for(NumberDto numberDto:listOfNumbers){
            sumOfAll = sumOfAll+numberDto.key;
        }
        if (zeroNumberValue == fiveNumberValue){
            Random random = new Random();

            Integer randomValue = random.nextInt(2);
              // // System.out.println("random number is : "+randomValue);
            if (randomValue == 0){
                finalWonValue = 0;
            }else {
                finalWonValue = 5;
            }
        }else if (zeroNumberValue>fiveNumberValue){
            Integer nintyFivePersent = (int) ((0.90) * (sumOfAll-zeroNumberValue));
            // System.err.println(sumOfAll +" "+nintyFivePersent+" "+zeroNumberValue);
            if (zeroNumberValue>0 && zeroNumberValue<nintyFivePersent){
                finalWonValue = 0;
            }
        }else if (zeroNumberValue<fiveNumberValue){
            Integer nintyFivePersent = (int) ((0.90) * (sumOfAll-fiveNumberValue));
            // System.err.println(fiveNumberValue>0 && fiveNumberValue<nintyFivePersent);
            if (fiveNumberValue>0 && fiveNumberValue<nintyFivePersent){
                finalWonValue = 5;
            }
        }


        return finalWonValue;


}
    @Override
    public Object getResult() {
        List<ChartTrend> chartTrends = chartTrendRepo.findByDoneStatus("_DONE_");
        chartTrends.sort(Comparator.comparing(ChartTrend::getId).reversed());
        List<ChartTrend> top50ChartTrends = chartTrends.size() > 50 ? chartTrends.subList(0, 50) : chartTrends;

        return top50ChartTrends;
    }

    @Override
    public List<GameColorNumber> getOrderByUserRefIdDone(String referanceId) {
        List<GameColorNumber> gameColorNumbers =gameColorNumberRepo.findByRefAndPeriodAndWinStatus(referanceId,true);
        if (gameColorNumbers.size()<=0)throw new RuntimeException("game Color Numbers not found by referance ID or period ");
        return gameColorNumbers;
    }

    @Override
    public List<GameColorNumber> getOrderByUserRefIdRunning(String referanceId) {
        List<GameColorNumber> gameColorNumbers =gameColorNumberRepo.findByRefAndPeriodAndWinStatus(referanceId,false);
        if (gameColorNumbers.size()<=0)throw new RuntimeException("game Color Numbers not found by referance ID or period ");
        return gameColorNumbers;
    }

    public void saveNumberWonUserAmount(Integer number , Integer color) {
        Boolean black = false;
        Boolean red =false;
        Boolean yellow =false;
        Boolean zero =false;
        Boolean one =false;
        Boolean two =false;
        Boolean three =false;
        Boolean four =false;
        Boolean five =false;
        Boolean six =false;
        Boolean seven =false;
        Boolean eight =false;
        Boolean nine =false;
        //      _YELLOW_  == 101
//        _RED_   == 102
//        _BLACK_ == 103

        if (101==color) {
            yellow =true;
        }
        else if (102==color) {
           red=true;
        }
        else if (103==color) {
            black = true;
        }

        //numbers//
        if (number == 0) {
            zero = true;
        }
        else if (number == 1) {
            one = true;

        }
        else if (number == 2) {
            two = true;

        }
        else if (number == 3) {
            three = true;

        }
        else if (number == 4) {
            four= true;

        }
        else if (number == 5) {
            five = true;

        }
        else if (number == 6) {
            six = true;

        }
        else if (number == 7) {
            seven = true;

        }
        else if (number == 8) {
            eight = true;

        }
        else if (number == 9) {
            nine = true;
        }




            List<GameColorNumber> gameColorNumbers1 = new LinkedList<>();
            List<GameColorNumber> gameColorNumbers = gameColorNumberRepo.findAllWinStatus(false);
            for (GameColorNumber gameColorNumber:gameColorNumbers){
                if (gameColorNumber.getType().equals("_NUMBER_")){
                    //numbers//
                    if (gameColorNumber.getZero() && zero) {
                        gameColorNumbers1.add(gameColorNumber);
                    }
                    else if (gameColorNumber.getOne() && one) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getTwo() && two) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getThree() && three) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getFour() && four) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getFive() && five) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getSix() && six) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getSeven() && seven) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getEight() && eight) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getNine() && nine) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    gameColorNumber.setWonNumber(number);

                }
                else if (gameColorNumber.getType().equals("_COLOR_")){
                    if (gameColorNumber.getBlack() && black) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getRed() && red) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    else if (gameColorNumber.getYellow() && yellow) {
                        gameColorNumbers1.add(gameColorNumber);

                    }
                    System.out.println("inside color 824");
                    gameColorNumber.setWonNumber(color);
                }


                gameColorNumber.setWinStatus(true);
            }
        System.err.println("% % % % % % % % % %"+gameColorNumbers1.size()+" % % % % % % % % % % %");

        System.out.println("list"+gameColorNumbers);
            HashMap <String,Integer> referanceIdANDAmount = new HashMap<>();
            List<Integer> list= new LinkedList<>();
            for (GameColorNumber gameColorNumber:gameColorNumbers1){
//               list.add(gameColorNumber.)
                if (((gameColorNumber.getType()).equals("_NUMBER_"))&&(gameColorNumber.getZero() ||gameColorNumber.getFive()) && referanceIdANDAmount.containsKey(gameColorNumber.getUserReferenceId())){
                    Integer amountUpdate = (int) (referanceIdANDAmount.get(gameColorNumber.getUserReferenceId()) + (((gameColorNumber.getAmount())*0.5)+((gameColorNumber.getAmount()*0.5) * 4.5)));

                    referanceIdANDAmount.put(gameColorNumber.getUserReferenceId(),amountUpdate);
                }else if (((gameColorNumber.getType()).equals("_NUMBER_")) && referanceIdANDAmount.containsKey(gameColorNumber.getUserReferenceId())){
                    Integer amountUpdate = (int) (referanceIdANDAmount.get(gameColorNumber.getUserReferenceId()) + ((gameColorNumber.getAmount())+(gameColorNumber.getAmount() * 8.5)));

                    referanceIdANDAmount.put(gameColorNumber.getUserReferenceId(),amountUpdate);
                }else if (((gameColorNumber.getType()).equals("_COLOR_")) && referanceIdANDAmount.containsKey(gameColorNumber.getUserReferenceId())){
                    Integer amountUpdate = (int) (referanceIdANDAmount.get(gameColorNumber.getUserReferenceId()) + ((gameColorNumber.getAmount())+(gameColorNumber.getAmount() * 0.85)));

                    referanceIdANDAmount.put(gameColorNumber.getUserReferenceId(),amountUpdate);
                }else if ((gameColorNumber.getType()).equals("_COLOR_")){
                    list.add(gameColorNumber.getUserId());
                    Integer amountUpdate = (int) ((gameColorNumber.getAmount())+(gameColorNumber.getAmount() * 0.85));

                    referanceIdANDAmount.put(gameColorNumber.getUserReferenceId(),amountUpdate);

                }else if (((gameColorNumber.getType()).equals("_NUMBER_"))){
                    list.add(gameColorNumber.getUserId());
                    Integer amountUpdate = (int) (gameColorNumber.getAmount() +(gameColorNumber.getAmount() * 8.5));

                    referanceIdANDAmount.put(gameColorNumber.getUserReferenceId(),amountUpdate);

                }
                System.err.println("hashmap entity : AMOUNT "+referanceIdANDAmount.get(gameColorNumber.getUserReferenceId())+" KEY : "+gameColorNumber.getUserReferenceId());

            }
            List<User> userList = userRepository.findAllById(list);
        System.err.println("inside of user save all size "+userList.size());
            for (User user : userList){
                Float totalBalance = user.getTotalBalnce() + referanceIdANDAmount.get(user.getReferenceId());
                System.err.println("888"+totalBalance);
                System.err.println(user.getTodayReferralBalance());
                System.err.println( (referanceIdANDAmount.get(user.getReferenceId()) * 0.03));
                Float todayReferralBalance = (float) (user.getTodayReferralBalance() + (referanceIdANDAmount.get(user.getReferenceId()) * 0.03));
                user.setTotalBalnce(totalBalance);
                user.setTodayReferralBalance(todayReferralBalance);
            }
        System.err.println("before u saveall");
            userRepository.saveAll(userList);
        System.err.println("before u game");

        gameColorNumberRepo.saveAll(gameColorNumbers);

        }

        //            List<User>
    }


