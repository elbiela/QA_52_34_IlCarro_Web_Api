package enums;

public enum HeaderMenu {
    LOGO("//img[@alt='Logo']"),
    SEARCH("//a[text()=' Search ']"),
    LET_THE_CAR_WORK("//a[@href='/let-car-work']"),
    TERMS_OF_USE("//a[@href='/terms-of-use']"),
    SIGN_UP("//a[text()=' Sign up ']"),
    LOGIN("a[text()=' Log in ']"),
    LOGOUT("a[text()=' Logout ']"),
    DELETE_ACCOUNT("//div[@class='header']//a[text()='Delete account']");

    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
