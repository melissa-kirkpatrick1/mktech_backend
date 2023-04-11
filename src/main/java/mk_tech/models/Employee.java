package mk_tech.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Employee extends BaseObject {
    private String userName;
    private String password;
    private String position;
    private Boolean administrator;
    private Boolean active;
    private Date startDate;
    private Date terminationDate;
    private Long billableHoursReq;
    private Long compTime;
    private String fullName;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_hour_type",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "hour_type_id")
    )
    private List<HourType> hourTypes;

    public Boolean getAdministrator() {
        return administrator != null && administrator ? true : false;
    }
    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getTerminationDate() {
        return terminationDate;
    }
    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }
    public Long getBillableHoursReq() {
        return billableHoursReq;
    }
    public void setBillableHoursReq(Long billableHoursReq) {
        this.billableHoursReq = billableHoursReq;
    }
    public Long getCompTime() {
        return compTime;
    }
    public void setCompTime(Long compTime) {
        this.compTime = compTime;
    }
//    public List<Contract> getContracts() {
//        return contracts;
//    }
//    public void setContracts(List<Contract> contracts) {
//        this.contracts = contracts;
//    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setHourTypes(List<HourType> hourTypes) {
        this.hourTypes = hourTypes;
    }

    public List<HourType> getHourTypes() {
        return hourTypes;
    }
}
