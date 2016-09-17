package appliance51.rest.model;

/**
 * Created by yuananyun on 2016/9/11.
 */
public enum AccountType {
    Proprietor("proprietor"), Workman("workman");

    private String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
