package com.example.customerjwt.service;

import com.example.customerjwt.dto.CustomerRequest;
import com.example.customerjwt.entity.Customer;
import com.example.customerjwt.exception.ResourceNotFoundException;
import com.example.customerjwt.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer create(CustomerRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already exists: " + req.email());
        }
        Customer c = new Customer();
        apply(c, req);
        return repo.save(c);
    }

    public List<Customer> findAll() {
        return repo.findAll();
    }

    public Customer findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public Customer update(Long id, CustomerRequest req) {
        Customer c = findById(id);
        if (repo.existsByEmailAndIdNot(req.email(), id)) {
            throw new IllegalArgumentException("Email already exists: " + req.email());
        }
        apply(c, req);
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.delete(findById(id));
    }

    private void apply(Customer c, CustomerRequest req) {
        c.setName(req.name());
        c.setEmail(req.email());
        c.setPhone(req.phone());
        c.setAddress(req.address());
    }
}
