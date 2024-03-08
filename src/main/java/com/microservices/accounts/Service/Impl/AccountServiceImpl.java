package com.microservices.accounts.Service.Impl;

import com.microservices.accounts.Constants.AccountConstants;
import com.microservices.accounts.DTO.AccountDTO;
import com.microservices.accounts.DTO.CustomerDTO;
import com.microservices.accounts.Enitity.Account;
import com.microservices.accounts.Enitity.Customer;
import com.microservices.accounts.Exception.CustomerAlreadyExistsException;
import com.microservices.accounts.Exception.ResourceNotFoundException;
import com.microservices.accounts.Mapper.AccountMapper;
import com.microservices.accounts.Mapper.CustomerMapper;
import com.microservices.accounts.Repository.AccountRepository;
import com.microservices.accounts.Repository.CustomerRepository;
import com.microservices.accounts.Service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer= CustomerMapper.mapToCustomer(customerDTO,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException
                    ("Customer is already exists for a given mobile number"+customerDTO.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymus");
        Customer savedCustomer=customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );
        Account account=accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","Customer Id",customer.getCustomerId().toString())
        );
        CustomerDTO customerDTO=CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
        customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account,new AccountDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated=false;
        AccountDTO accountDTO=customerDTO.getAccountDTO();
        if(accountDTO!=null) {
            Account account = accountRepository.findById(accountDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account Number",
                            accountDTO.getAccountNumber().toString())
            );
            AccountMapper.mapToAccount(accountDTO, account);
            accountRepository.save(account);
            Long customerId=account.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted=false;
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );
        Long customerId= customer.getCustomerId();
        if(customer!=null){
            accountRepository.deleteByCustomerId(customerId);
            customerRepository.deleteById(customerId);
            isDeleted=true;
        }

        return isDeleted;
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymus");
        return newAccount;
    }
}
