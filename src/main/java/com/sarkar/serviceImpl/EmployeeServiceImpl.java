package com.sarkar.serviceImpl;

import com.sarkar.entity.Employee;
import com.sarkar.repository.EmployeeRepository;
import com.sarkar.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public Employee createUser(Employee employee) {
        return empRepo.save(employee);

    }

    @Override
    public List<Employee> getAllUsers() {

        return empRepo.findAll();
    }

    @Override
    public Optional<Employee> getUserById(Long id) {
        return empRepo.findById(id);
    }

    public Employee updateUser(Long id, Employee emp) {
        // Try to find the employee by id
        Optional<Employee> existingEmployee = empRepo.findById(id);

        // If the employee exists, update and save it
        if (existingEmployee.isPresent()) {
            Employee existing = existingEmployee.get();
            existing.setName(emp.getName());
            existing.setEmail(emp.getEmail());
            existing.setPassword(emp.getPassword());
            existing.setPhno(emp.getPhno());
            return empRepo.save(existing);  // Save and return updated employee
        }

        return null;  // Return null if employee is not found
    }


    @Override
    public void deleteUser(Long id) {

        empRepo.deleteById(id);


    }

}
