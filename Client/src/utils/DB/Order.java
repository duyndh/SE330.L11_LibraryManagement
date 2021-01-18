package utils.DB;

public enum Order {
    ASC("asc"),
    DESC("desc");

    private final String label;

    public String getLabel() {
        return this.label;
    }

    Order(String label) {
        this.label = label;
    }
}
