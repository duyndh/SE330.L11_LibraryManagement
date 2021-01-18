package utils.DB;

public enum Operator {
    Equal("="),
    Larger(">"),
    Smaller("<"),
    LargerOrEqual(">="),
    SmallerOrEqual("<=");

    private final String sym;

    public String getSym() {
        return this.sym;
    }

    Operator(String sym) {
        this.sym = sym;
    }
}
