package mk_tech.security;

import mk_tech.models.Employee;
import mk_tech.repository.EmployeeRepository;
import mk_tech.service.EmployeeService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    CustomAuthenticationProvider(EmployeeRepository employeeRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String password = authentication.getCredentials().toString();
        Employee employee = this.employeeRepository.findByUserNameIgnoreCase(authentication.getName());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        UserDetails userDetails = null;
        if (employee != null) {
            System.out.println("############# -- EMPLOYEE NOT NULL");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, employee.getPassword())) {
                System.out.println("############# -- PASSWORD MATCHES");
                if (employee.getAdministrator()) {
                    GrantedAuthority auth = new SimpleGrantedAuthority("admin");
                    grantedAuthorities.add(auth);
                    System.out.println("############# -- MAKING ADMIN");
                }
                System.out.println("############# -- USER: "+employee.getUserName());
                System.out.println("############# -- PASS: "+employee.getPassword());
                System.out.println("############# -- AUTH SIZE: "+grantedAuthorities.size());
                userDetails = new User(employee.getUserName(), employee.getPassword(), grantedAuthorities);

            } else {
                System.out.println("#############-- PASSWORD DOES NOT MATCH");
            }
        }
        if (userDetails != null) {
            System.out.println("#############-- USER DETAILS NOT NULL");
            employee.setPassword("");
            return new UsernamePasswordAuthenticationToken(
                    employee, userDetails.getPassword(), userDetails.getAuthorities());
        } else {
            System.out.println("#############-- USER DETAILS NULL");
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
