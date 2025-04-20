package com.microservices.eazybank.Accounts.service.impl;

import com.microservices.eazybank.Accounts.constants.AccountsConstants;
import com.microservices.eazybank.Accounts.dto.CustomerDto;
import com.microservices.eazybank.Accounts.entity.Accounts;
import com.microservices.eazybank.Accounts.entity.Customer;
import com.microservices.eazybank.Accounts.exception.CustomerAlreadyExistsException;
import com.microservices.eazybank.Accounts.mapper.CustomerMapper;
import com.microservices.eazybank.Accounts.repository.AccountsRepository;
import com.microservices.eazybank.Accounts.repository.CustomerRepository;
import com.microservices.eazybank.Accounts.service.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class IAccountServiceImpl implements IAccountService {
    
    private  AccountsRepository accountsRepository;
    private  CustomerRepository customerRepository;

    @Autowired
    public IAccountServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * @param customerDto - AccountDto
     *                    This method creates an account along with customer
     */
    @Override
    @Transactional
    public void createAccount(CustomerDto customerDto) {
        Customer customer=CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with the given mobile number "+customerDto.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private static Accounts createNewAccount(Customer customer) {
        Accounts accounts=new Accounts();
        //setting properties
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 2000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        return accounts;
    }
}
