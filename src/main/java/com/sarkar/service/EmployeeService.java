package com.sarkar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sarkar.entity.Employee;

@Service
public interface EmployeeService {
	
	public Employee createUser(Employee employee);
	
	public List<Employee> getAllUsers();
	
	Optional<Employee> getUserById(Long id);
	
	Employee updateUser(Long id,Employee emp);
	
	void deleteUser(Long id);
	

}
