package com.sarkar.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sarkar.entity.Employee;
import com.sarkar.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

	@Autowired
	private EmployeeService empService;

	@PostMapping("/create")
	public ResponseEntity<Employee> createUser(@RequestBody Employee employee) {
		Employee user = empService.createUser(employee);

		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}

	@GetMapping("/getEmp")
	public List<Employee> getUserById() {
		return empService.getAllUsers();

	}

	@GetMapping("/emp")
	public ResponseEntity<Employee> getUserById(@RequestParam Long id) {

		return empService.getUserById(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}

	@PutMapping("/update")
	public ResponseEntity<Employee> updateUser(@RequestParam Long id, @RequestBody Employee emp) {
	    Employee updatedEmployee = empService.updateUser(id, emp);
	    
	    if (updatedEmployee == null) {
	        // If employee was not found, return 404
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(null);
	    }
	    return ResponseEntity.ok(updatedEmployee);
	}


	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
		try {
			empService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
