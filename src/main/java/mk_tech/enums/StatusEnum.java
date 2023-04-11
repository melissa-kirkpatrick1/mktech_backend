package mk_tech.enums;

public enum StatusEnum {
    ACTIVE("Active"),
    REACTIVATED("Reactivated"),
    SUBMITTED("Submitted"),
    APPROVED("Approved");

    public final String label;

    private StatusEnum(String label) {
        this.label = label;
    }
}
