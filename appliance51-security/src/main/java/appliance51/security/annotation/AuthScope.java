package appliance51.security.annotation;

/**
 * Created by yuananyun on 2016/9/20.
 */
public enum AuthScope {
    //业主端
    PROPRIETOR("proprietor"),
    //师傅端
    WORKMAN("workman"),
    //所有端
    ALL("all");

    AuthScope(String value) {
        this.value = value;
    }

    private String value;
    public String getValue(){
        return value;
    }
}
