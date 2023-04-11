package mk_tech.repository;
import mk_tech.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByUserNameIgnoreCase(String userName);
}
