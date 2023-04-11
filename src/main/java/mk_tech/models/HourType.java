package mk_tech.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class
HourType extends BaseObject {
    public static final String FLOAT_CODE = "FHOL";
    public static final String PTO_CODE = "PTO";
    public static final String COMP_CODE = "COMP";

    @Enumerated(EnumType.STRING)
    private HourCategory hourCategory;

    @ManyToOne
    @JoinColumn(name="contract_id")
    private Contract contract;

    @JsonIgnore
    @ManyToMany(mappedBy = "hourTypes")
    private List<Employee> employees;

    private String code;
    private String name;
    private Boolean allTimecards;
    private Boolean billableHour;
    private String shortName;
    private String narrative;
    private Boolean overheadHour;

    private Long sortOrder;

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean isAllTimecards() {
        return allTimecards;
    }

    public void setAllTimecards(Boolean allTimecards) {
        this.allTimecards = allTimecards;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Boolean isBillableHour() {
        return billableHour;
    }

    public void setBillableHour(Boolean billableHour) {
        this.billableHour = billableHour;
    }

    public Boolean isOverheadHour() {
        return overheadHour;
    }

    public void setOverheadHour(Boolean overheadHour) {
        this.overheadHour = overheadHour;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HourCategory getHourCategory() {
        return hourCategory;
    }

    public void setHourCategory(HourCategory hourCategory) {
        this.hourCategory = hourCategory;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public Boolean getAllTimecards() {
        return allTimecards;
    }

    public Contract getContract() {
        return contract;
    }

    public Boolean getBillableHour() {
        return billableHour;
    }

    public Boolean getOverheadHour() {
        return overheadHour;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }


}
