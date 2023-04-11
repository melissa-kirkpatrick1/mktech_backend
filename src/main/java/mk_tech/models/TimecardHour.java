package mk_tech.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.AutoPopulatingList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TimecardHour extends BaseObject {
    @Temporal(TemporalType.TIMESTAMP)
    private Date hourDate;

    @ManyToOne
    @JoinColumn(name="hour_type_id")
    private HourType hourType;

    private int payPeriod;
    private Double numHours;

    @ManyToOne
    @JoinColumn(name="timecard_id")
    @JsonIgnore
    private Timecard timecard;

    public Date getHourDate() {
        return hourDate;
    }

    public void setHourDate(Date hourDate) {
        this.hourDate = hourDate;
    }

    public Double getNumHours() {
        return numHours;
    }

    public void setNumHours(Double numHours) {
        this.numHours = numHours;
    }

    public HourType getHourType() {
        return hourType;
    }

    public void setHourType(HourType hourType) {
        this.hourType = hourType;
    }

    public Timecard getTimecard() {
        return timecard;
    }

    public void setTimecard(Timecard timecard) {
        this.timecard = timecard;
    }

    public int getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(int payPeriod) {
        this.payPeriod = payPeriod;
    }
}
