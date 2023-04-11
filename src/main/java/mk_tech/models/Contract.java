package mk_tech.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
public class Contract extends BaseObject {
    private String name;
    private String description;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "contracts")
//    private List<Employee> employees;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
}

