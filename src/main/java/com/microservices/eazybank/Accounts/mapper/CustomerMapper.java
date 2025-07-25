package com.microservices.eazybank.Accounts.mapper;

import com.microservices.eazybank.Accounts.dto.CustomerDto;
import com.microservices.eazybank.Accounts.entity.Customer;

public class CustomerMapper {

  private CustomerMapper() {
  }

  public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
    customerDto.setName(customer.getName());
    customerDto.setEmail(customer.getEmail());
    customerDto.setMobileNumber(customer.getMobileNumber());
    return customerDto;
  }

  public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setMobileNumber(customerDto.getMobileNumber());
    return customer;
  }
}
