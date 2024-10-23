package muthu.backend.driver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muthu.backend.driver.dao.EmployeeDao;
import muthu.backend.driver.dto.Employee;

@Service
public class EmployeeService {
   
	@Autowired
	private EmployeeDao employeedao;
	
	public Employee saveEmployee(Employee e) {
		
		return employeedao.saveEmployee(e);
	}
	
	public List<Employee> fetchEmployees(){
		return employeedao.FetchAllEmployee();
		
	}
	
	public Employee fetchById(int id) {
		
		return employeedao.fetchById(id);
	}
	
	public String deleteById(int id) {
		
		return employeedao.deleteById(id);
	}
	
	public Employee updateEmployeeSal(int id, double sal) {
		
		Employee e= employeedao.fetchById(id);
		if(e!=null) {
			e.setSal(sal);
			
			return employeedao.update(e);
		}
		return null;
		
	}
	
	public Employee Updateall(int id, Employee New_emp) {
		
		Employee db_Emp = employeedao.fetchById(id);
		
		if(db_Emp!=null) {
			New_emp.seteId(id);
			return employeedao.update(New_emp);
		}
		return null;
	}
	
}
