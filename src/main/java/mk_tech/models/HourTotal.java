package mk_tech.models;

public class HourTotal {
    private String hourTypeName;
    private Double totalHours;
    private Boolean billableHour;

    public Double getTotalHours() {
        return totalHours;
    }

    public void setHourTypeName(String hourTypeName) {
        this.hourTypeName = hourTypeName;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public String getHourTypeName() {
        return hourTypeName;
    }

    public Boolean getBillableHour() {
        return billableHour;
    }
    public void setBillableHour(Boolean billableHour) {
        this.billableHour = billableHour;
    }
}
