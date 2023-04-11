package mk_tech.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Week extends BaseObject{
    private Date startWeekDate;
    private Date endWeekDate;
    private Integer weekNumber;

    public Date getEndWeekDate() {
        return endWeekDate;
    }
    public void setEndWeekDate(Date endWeekDate) {
        this.endWeekDate = endWeekDate;
    }
    public Date getStartWeekDate() {
        return startWeekDate;
    }
    public void setStartWeekDate(Date startWeekDate) {
        this.startWeekDate = startWeekDate;
    }
    public Integer getWeekNumber() {
        return weekNumber;
    }
    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }
}
