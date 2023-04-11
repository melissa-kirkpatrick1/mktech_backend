package mk_tech.service;

import mk_tech.models.Employee;
import mk_tech.repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) this.employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            employee.setPassword(passwordEncoder.encode("password"));
        }
        this.employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeByUserName(String userName) {
        return this.employeeRepository.findByUserNameIgnoreCase(userName);
    }

    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id).get();
    }
}
