package mk_tech.controllers;

import mk_tech.models.Employee;
import mk_tech.service.EmployeeService;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("emp-api")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> listEmployees(Authentication authentication) {
        return this.employeeService.getEmployees();
    }

    @PostMapping(path="/employee", consumes = "application/json", produces = "application/json")
    public Employee saveEmployee(@RequestBody Employee employee, Authentication authentication) {
        return this.employeeService.saveEmployee(employee);
    }
    @GetMapping(path = "/employee/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return this.employeeService.getEmployeeById(employeeId);
    }
    @PostMapping(path="/employee-password", consumes = "application/json", produces = "application/json")
    public Employee updateEmployeePassword(@RequestBody JSONObject passwordMap, Authentication authentication) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Employee employee = this.employeeService.getEmployeeByUserName(passwordMap.get("userName").toString());
        employee.setPassword(passwordEncoder.encode(passwordMap.get("password").toString()));
        return this.employeeService.saveEmployee(employee);
    }
}
