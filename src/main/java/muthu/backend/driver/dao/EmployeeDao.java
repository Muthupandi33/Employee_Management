package muthu.backend.driver.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import muthu.backend.driver.dto.Employee;
import muthu.backend.driver.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	
	@Autowired
	private EmployeeRepo Repo;
	
	public Employee saveEmployee(Employee e) {
		
		return Repo.save(e);
	}
	

	public List<Employee> FetchAllEmployee() {
		
		return  Repo.findAll();
	}
	
	public Employee fetchById(int Id) {
		
		Optional<Employee> e= Repo.findById(Id);
		
		if(e.isPresent()){
			return e.get();			
		}
		return null;
	}
    public String deleteById(int id) {
    	
    	Repo.deleteById(id);
    	return "Data Deleted";
    	
    }


   public Employee update(Employee e) {
	   
	   return Repo.save(e);
	   
   }





}
