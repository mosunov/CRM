package com.crm.service;

import com.crm.entity.Customer;
import com.crm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(String phone) {
        return customerRepository.findById(phone);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
