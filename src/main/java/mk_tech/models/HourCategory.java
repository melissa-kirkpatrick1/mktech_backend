package mk_tech.models;

public enum HourCategory {
    CONTRACT("Contract", "CONTRACT"),
    UNCHARGE("Uncharged", "UNCHARGE"),
    UNPAID("Unpaid Time Off", "UPTO"),
    OH("Overhead", "OVERHEAD"),
    HOL("Holiday", "HOLIDAY"),
    PTO("PTO", "PTO"),
    COMP("Comp Time", "COMP"),
    G_AND_A("General & Administrative", "G_AND_A");

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    private HourCategory(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static HourCategory findByCode(String code) {
        for (HourCategory category: HourCategory.values()) {
            if (category.code.equals(code))
                return category;
        }
        return null;

    }
}
