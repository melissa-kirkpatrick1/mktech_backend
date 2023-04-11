package mk_tech.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mk_tech.enums.StatusEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Timecard extends BaseObject{
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="week_id")
    private Week week;

    @Column(columnDefinition="TEXT")
    private String comments;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="timecard_id")
    @Where(clause="end_date is null")
    private List<TimecardHour> hours = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="timecard_id")
    @Where(clause="end_date is null")
    private List<Note> notes = new ArrayList<>();
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }
    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public List<TimecardHour> getHours() {
        return hours;
    }
    public void setHours(List<TimecardHour> hours) {
        this.hours = hours;
    }
    public Date getSubmittedDate() {
        return submittedDate;
    }
    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    public List<Note> getNotes() {
        return notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
    public void setWeek(Week week) {
        this.week = week;
    }
    public Week getWeek() {
        return week;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
