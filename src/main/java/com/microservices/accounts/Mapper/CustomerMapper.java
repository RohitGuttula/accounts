package com.microservices.accounts.Mapper;

import com.microservices.accounts.DTO.CustomerDTO;
import com.microservices.accounts.Enitity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO){
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO,Customer customer){
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
