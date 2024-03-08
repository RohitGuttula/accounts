package com.microservices.accounts.Service;

import com.microservices.accounts.DTO.CustomerDTO;

public interface IAccountService {

    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}
