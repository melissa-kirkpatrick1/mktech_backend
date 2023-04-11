package mk_tech.service;

import mk_tech.models.Employee;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getEmployees();
    Employee getEmployeeByUserName(String userName);
    Employee getEmployeeById(Long id);
}
