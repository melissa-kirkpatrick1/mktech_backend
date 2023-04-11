package mk_tech.models;

import java.util.Date;
import java.util.List;

public class HourSummary {
    private Long weekNumber;
    private Date weekStartDate;
    private List<HourTotal> hourTotals;
    public List<HourTotal> getHourTotals() {
        return hourTotals;
    }

    public Long getWeekNumber() {
        return weekNumber;
    }

    public void setHourTotals(List<HourTotal> hourTotals) {
        this.hourTotals = hourTotals;
    }

    public void setWeekNumber(Long weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }
}
