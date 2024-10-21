package com.spring.jwt.service;

import com.spring.jwt.dto.*;
import com.spring.jwt.entity.*;
import com.spring.jwt.exception.*;
import com.spring.jwt.repository.*;
import com.spring.jwt.Interfaces.UserService;
import com.spring.jwt.utils.BaseResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private ProfitRepo profitRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RechargeTransactionRepo rechargeTransactionRepo;
    @Autowired
    private WithdrawRepo withdrawRepo;


    @Override
    public BaseResponseDTO registerAccount(RegisterDto registerDto) throws RuntimeException{
        BaseResponseDTO response = new BaseResponseDTO();

        validateAccount(registerDto);
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setMobileNo(registerDto.getMobileNo());
        user.setReferralId(registerDto.getReferralId().isEmpty() ? "NO_REFERRAL_ID" : registerDto.getReferralId());

        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setTotalBalnce(0.00f);
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(registerDto.getRoles());
        roles.add(role);
        user.setRoles(roles);
//        User user = insertUser(registerDto);

        Random random = new Random();
        Boolean flag = true;
        Integer randomReferenceNumber = 0;
        String character = "";
        if((role.getName()).equals("USER")) character = "U";
        else if((role.getName()).equals("ADMIN")) character = "A";
        else if((role.getName()).equals("SUPERADMIN")) character = "SA";
        while(flag){
            randomReferenceNumber =  random.nextInt(89999999)+9999999;


            Optional<User> user1 = userRepository.findByReferenceId(randomReferenceNumber+""+character);
            if(user1.isEmpty())flag = false;

            System.err.println("inside whileloop");
            System.out.println(user1.toString());
        }
        String ReferenceIds = String.valueOf(randomReferenceNumber);
        System.out.println(ReferenceIds+character);
        System.out.println(character);
        user.setReferenceId(ReferenceIds+"_"+character);
        if (!(user.getReferralId()).equals("NO_REFERRAL_ID")){
            System.err.println(user.getReferralId());

            User referralUser =  userRepository.findByReferenceId(user.getReferralId()).orElseThrow(()->new RuntimeException("referral invalid"));
            String referralId = user.getReferralId();
            referralUser.getReferralIdList().add(ReferenceIds+"_"+character);

            System.err.println(user.getReferralId());


        }




        try {
            userRepository.save(user);
            response.setCode(String.valueOf(HttpStatus.OK.value()));
            response.setMessage("Account Created");
        } catch (UserAlreadyExistException e) {
            response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            response.setMessage("User already exist");
        }catch (BaseException e){
            response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            response.setMessage("Invalid role");
        }
        return response;
    }

    @Override
    public User getByReferenceIdAdminRecharge(String referenceId) {
        User user = userRepository.findByReferenceId(referenceId).orElseThrow(()->new RuntimeException("reference name not found by id"));
        return user;


    }

    @Override
    public String recharge(String postUserId, String getUserId,Float amount) {
        User senderDetails = userRepository.findByReferenceId(postUserId).orElseThrow(()->new RuntimeException("post reference name not found by id"));
        User receiverDetails = userRepository.findByReferenceId(getUserId).orElseThrow(()->new RuntimeException("get reference name not found by id"));
        System.out.println("senderDetails.getTotalBalnce()"+senderDetails.getTotalBalnce()+" Amount : "+amount);

        if(senderDetails.getTotalBalnce() >= amount){



            Float transactionFees = amount*0.05f;
//          BELOW CODE FOR ADMIN MEAN SENDER THAT MONEY WILL DEDUCTED

            if((senderDetails.getTotalBalnce())<(amount+transactionFees))
                senderDetails.setTotalBalnce((amount+transactionFees)-(senderDetails.getTotalBalnce()));
            else if ((amount+transactionFees)<(senderDetails.getTotalBalnce()))
                senderDetails.setTotalBalnce((senderDetails.getTotalBalnce())-(amount+transactionFees));

//           BELOW CODE FOR TANSACTION FEES
//            Float totalBalanceAfterTransactionFees = amount-transactionFees;
//
//            receiverDetails.setTotalBalnce(receiverDetails.getTotalBalnce()+totalBalanceAfterTransactionFees);


            receiverDetails.setTotalBalnce(receiverDetails.getTotalBalnce()+amount);

            RechargeTransactions rechargeTransactions = RechargeTransactions.builder()
                    .transactionAmount(amount)
                    .rechargeTransactionsDateAndTime(LocalDateTime.now())
                    .rechargeSenderId(senderDetails.getReferenceId())
                    .rechargeReceiverId(receiverDetails.getReferenceId())
                    .rechargeTransactionsStatus(true)
                    .transactionFees(0.00f)
                    .build();


//            Profit profit = Profit.builder()
//                            .TransactionsDateAndTime(LocalDateTime.now())
//                                    .rechargeSenderId(senderDetails.getReferenceId())
//                                            .receiverId(receiverDetails.getReferenceId())
//                                                    .transactionsStatus(true)
//                                                            .transactionFees(transactionFees)
//                                                                    .status("SUCCESS")
//                                                                            .transactionAmount(amount)
//                                                                                    .sourceOfProfit("Recharge")
//                                                                                            .build();

//            profitRepo.save(profit);

            userRepository.save(senderDetails);
            rechargeTransactionRepo.save(rechargeTransactions);
            userRepository.save(receiverDetails);

        }else if (senderDetails.getTotalBalnce() < amount){
            throw new RuntimeException("recharge unsuccess check your user wallet balance :: ADMINID => "+postUserId );
        }
        return "recharge success";
    }

    @Override
    public String withdraw(String postUserId, String getUserId, Float amount) {
        User senderDetails = userRepository.findByReferenceId(postUserId).orElseThrow(()->new RuntimeException("post reference name not found by id"));
        User receiverDetails = userRepository.findByReferenceId(getUserId).orElseThrow(()->new RuntimeException("get reference name not found by id"));

        if(receiverDetails.getTotalBalnce() >= amount){
            Float transactionFees = amount*0.10f;
            Float totalBalanceAfterTransactionFees = amount-transactionFees;


            WithdrawTransaction withdrawTransaction = WithdrawTransaction.builder()
                    .transactionAmount(amount)
                    .withdrawTransactionsDateAndTime(LocalDateTime.now())
                    .rechargeSenderId(senderDetails.getReferenceId())
                    .withdrawReceiverId(receiverDetails.getReferenceId())
                    .withdrawTransactionsStatus(false)
                    .transactionFees(transactionFees)
                    .withdrowStatus("PENDING")
                    .build();




            withdrawRepo.save(withdrawTransaction);

        }else if (receiverDetails.getTotalBalnce() < amount){
            throw new RuntimeException("withdraw unsuccess check your user wallet balance :: USERID => "+getUserId );
        }
        return "withdraw permission proceed";
    }

    @Override
    public List<WithdrawTransaction> userNotificationList(String getUserId) {
//        User receiverDetails = userRepository.findByReferenceId(getUserId).orElseThrow(()->new RuntimeException("get reference name not found by id"));

        return withdrawRepo.findbyWithdrawStatus("PENDING",getUserId);
    }

    @Override
    public String withdrawApprovalUserSide(Integer withdrawId) {

        WithdrawTransaction withdrawTransactionDetails = withdrawRepo.findById(withdrawId).orElseThrow(()->new RuntimeException("withdraw transaction not found by id"));
        System.out.println("sender :"+withdrawTransactionDetails.getRechargeSenderId()+" receiver : "+withdrawTransactionDetails.getWithdrawReceiverId());
        User senderDetails = userRepository.findByReferenceId(withdrawTransactionDetails.getRechargeSenderId()).orElseThrow(()->new RuntimeException("post reference name not found by id"));
        User receiverDetails = userRepository.findByReferenceId(withdrawTransactionDetails.getWithdrawReceiverId()).orElseThrow(()->new RuntimeException("get reference name not found by id"));

        if(receiverDetails.getTotalBalnce() >= withdrawTransactionDetails.getTransactionAmount()){
            Float transactionFees = (withdrawTransactionDetails.getTransactionAmount())*0.05f;
            Float totalBalanceAfterTransactionFees = (withdrawTransactionDetails.getTransactionAmount())-transactionFees;

            senderDetails.setTotalBalnce((senderDetails.getTotalBalnce())+((withdrawTransactionDetails.getTransactionAmount())-(withdrawTransactionDetails.getTransactionFees()-transactionFees)));

          if (receiverDetails.getTotalBalnce()<= withdrawTransactionDetails.getTransactionAmount()) {
              receiverDetails.setTotalBalnce((receiverDetails.getTotalBalnce()) - (withdrawTransactionDetails.getTransactionAmount()));
          }else throw new RuntimeException("check your balance for withdraw");



            withdrawTransactionDetails.setWithdrawTransactionsDateAndTime(LocalDateTime.now());
            withdrawTransactionDetails.setWithdrawTransactionsStatus(true);
            withdrawTransactionDetails.setWithdrowStatus("SUCCESS");
            Profit profit = Profit.builder()
                    .TransactionsDateAndTime(LocalDateTime.now())
                    .rechargeSenderId(senderDetails.getReferenceId())
                    .receiverId(receiverDetails.getReferenceId())
                    .transactionsStatus(true)
                    .transactionFees(transactionFees)
                    .status("SUCCESS")
                    .transactionAmount(withdrawTransactionDetails.getTransactionAmount())
                    .sourceOfProfit("Withdraw")
                    .build();

            profitRepo.save(profit);
            userRepository.save(senderDetails);
            userRepository.save(receiverDetails);
            withdrawRepo.save(withdrawTransactionDetails);

        }else if (receiverDetails.getTotalBalnce() < (withdrawTransactionDetails.getTransactionAmount())){
            throw new RuntimeException("withdraw unsuccess  user wallet balance Low");
        }
        return "withdraw success";
    }

    @Override
    public String cancelWithdraw(Integer withdrawID) {
        WithdrawTransaction withdrawTransaction = withdrawRepo.findById(withdrawID).orElseThrow(()->new RuntimeException("withdraw transaction not found by id "));
            withdrawRepo.deleteById(withdrawID);
            return "withdraw Transaction deleted";
    }

    @Override
    public User getByUserId(Integer userID) {
        return userRepository.findById(userID).orElseThrow(()->new RuntimeException("user details not found by id"));
    }

    @Override
    public Boolean getStatusAfterWithdawProcced(String userRId) {
        Boolean b = false;
        List<WithdrawTransaction> withdrawTransactionList = withdrawRepo.findbyWithdrawStatus(userRId,"SUCCESS");

        if (withdrawTransactionList.size() == 1 && (withdrawTransactionList.get(1).getWithdrawTransactionsStatus()).equals("SUCCESS")){
            b = true;
        }else if (withdrawTransactionList.size()>1){
             withdrawRepo.deleteAll(withdrawTransactionList);
        }
        return b;
    }


    private void validateAccount(RegisterDto registerDto) {
        // validate null data
        if (ObjectUtils.isEmpty(registerDto)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Data must not be empty");
        }


        // validate duplicate username
        User user = userRepository.findByEmail(registerDto.getEmail());
        if (!ObjectUtils.isEmpty(user)) {
            throw new UserAlreadyExistException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Username already exists");
        }

        // validate role
        List<String> roles = roleRepository.findAll().stream().map(Role::getName).toList();
        if (!roles.contains(registerDto.getRoles())) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid role");
        }
    }



}
