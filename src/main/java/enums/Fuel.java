package enums;

public enum Fuel {
    DIESEL("Diesel"),
    PETROL("Petrol"),
    HYBRID("Hybrid"),
    ELECTRIC("Electric"),
    GAS("Gas");

    private final String value;

    Fuel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
