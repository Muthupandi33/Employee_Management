package muthu.backend.driver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import muthu.backend.driver.dto.Employee;
import muthu.backend.driver.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@PostMapping("saveemployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee e) {
		
		return new ResponseEntity<Employee>(employeeservice.saveEmployee(e), HttpStatus.CREATED);
	}

	@GetMapping("Getall")
	public List<Employee> FetchEmployees(){
		
		return employeeservice.fetchEmployees();
	}

	@GetMapping("getById/{id}")
	public ResponseEntity<Employee> fetchById(@PathVariable int id) {
		Employee e = employeeservice.fetchById(id);
		if(e!= null) {
			return new ResponseEntity<Employee>(e,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	
		}
	
    @DeleteMapping("DeleteById")
	public String deleteById(@RequestParam int id) {
		
		return employeeservice.deleteById(id);
	}
    
    @PutMapping("updateSal/{id}/{sal}")
    public ResponseEntity<Employee> updateEmployeeSal(@PathVariable int id, @PathVariable double sal) {
    	
    	return new ResponseEntity<Employee> (employeeservice.updateEmployeeSal(id, sal), HttpStatus.OK);
    }
    
    @PutMapping("updateAll/{id}")
    public Employee Updateall(@PathVariable int id, @RequestBody Employee New_emp){
    	
    	return employeeservice.Updateall(id, New_emp);
    }
    
	}

