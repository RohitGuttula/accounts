package com.microservices.accounts.Mapper;

import com.microservices.accounts.DTO.AccountDTO;
import com.microservices.accounts.Enitity.Account;

public class AccountMapper {

    public static AccountDTO mapToAccountDTO(Account account,AccountDTO accountDTO){
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBranchAddress(account.getBranchAddress());
        return accountDTO;
    }

    public static Account mapToAccount(AccountDTO accountDTO, Account account){
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBranchAddress(accountDTO.getBranchAddress());
        return account;
    }
}
