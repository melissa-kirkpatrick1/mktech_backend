package mk_tech.controllers;

import mk_tech.models.Employee;
import mk_tech.security.CustomAuthenticationProvider;
import mk_tech.service.EmployeeService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//@RestController
//@CrossOrigin
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("login-api")
public class LoginController {
    private final static String LOGGED_IN_USER = "LOGGED_IN_USER";
    private final EmployeeService employeeService;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public LoginController(EmployeeService employeeService,
                           CustomAuthenticationProvider customAuthenticationProvider) {
        this.employeeService = employeeService;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @PostMapping(path = "/login")
    public Employee login(Authentication authentication, @RequestBody Map<String, String> loginInfo,  HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(loginInfo.get("user"), loginInfo.get("password"));
        Authentication auth = customAuthenticationProvider.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();

        if (auth != null) {
            System.out.println("############## SET AUTH TO: "+auth.getPrincipal().getClass());
            sc.setAuthentication(auth);
            Employee employee = (Employee) auth.getPrincipal();

            request.getSession().setAttribute(LOGGED_IN_USER, employee);
            System.out.println("AUTH CLASS AFTER LOGGING IN: "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            return employee;
        }
        return null;
    }

    @GetMapping(value = "/logout")
    public Object logout(HttpServletRequest req) {
        SecurityContextHolder.clearContext();
        req.getSession().invalidate();
        return null;
    }
    @GetMapping(value="/user-details")
    public Object getUserDetails(Authentication authentication, HttpServletRequest request) {
        if (request.getSession().getAttribute(LOGGED_IN_USER) != null) {
            System.out.println("#### HAVE USE IN SESSION");
        }
        System.out.println("AUTH CLASS PRICIPAL IN DETAILS: "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
        System.out.println("AUTH CLASS IN DETAILS: "+SecurityContextHolder.getContext().getAuthentication().getClass());
        if ( SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Employee) {
            return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            if (request.getSession().getAttribute(LOGGED_IN_USER) != null) {
                return request.getSession().getAttribute(LOGGED_IN_USER);
            }
            else {
                System.out.println("########### NO EMPLOYEE IN SESSION EITHER");
                return null;
            }
        }

//        return threadLocalValue.get();
    }

}
