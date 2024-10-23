package muthu.backend.driver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import muthu.backend.driver.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
