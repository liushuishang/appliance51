package appliance51.rest.model;

/**
 * Created by yuananyun on 2016/9/11.
 */
public enum AcountStatus {
    /**
     * 未验证
     */
    NotVerified(0),
    /**
     * 已实名验证
     */
    Verified(1),
    /**
     * 已禁用
     */
    Disabled(2);

    AcountStatus(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

}
