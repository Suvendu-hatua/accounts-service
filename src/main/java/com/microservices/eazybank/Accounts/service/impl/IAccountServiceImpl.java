package com.microservices.eazybank.Accounts.service.impl;

import com.microservices.eazybank.Accounts.constants.AccountsConstants;
import com.microservices.eazybank.Accounts.dto.AccountsDto;
import com.microservices.eazybank.Accounts.dto.CustomerDto;
import com.microservices.eazybank.Accounts.entity.Accounts;
import com.microservices.eazybank.Accounts.entity.Customer;
import com.microservices.eazybank.Accounts.exception.CustomerAlreadyExistsException;
import com.microservices.eazybank.Accounts.exception.ResourceNotFoundException;
import com.microservices.eazybank.Accounts.mapper.AccountsMapper;
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

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        //finding customer by mobileNumber
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        //finding accounts details by customerId
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).
                orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
        //Data Transfer Object(DTO)
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        //Returning customerDTO
        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    @Transactional
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated=false;
       AccountsDto accountsDto=customerDto.getAccountsDto();
       if(accountsDto!=null){
           Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                   .orElseThrow(() -> new ResourceNotFoundException("Accounts", "Account Number", accountsDto.getAccountNumber().toString()));
           //Updating Accounts entity fields
           AccountsMapper.mapToAccounts(accountsDto,accounts);
           accounts=accountsRepository.save(accounts);
           //fetching Customer details
           Long customerId=accounts.getCustomerId();
           Customer customer = customerRepository.findById(customerId)
                   .orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer ID", customerId.toString()));
           //updating Customer entity fields
           CustomerMapper.mapToCustomer(customerDto,customer);
           customerRepository.save(customer);
           isUpdated=true;
       }else{
           Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                   .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", customerDto.getMobileNumber()));
           CustomerMapper.mapToCustomer(customerDto,customer);
           //saving the latest changes of Customer into DB
           customerRepository.save(customer);
           isUpdated=true;
       }
       return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted=false;
        //fetching customer by mobile number
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        isDeleted=true;
        return isDeleted;
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
