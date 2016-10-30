package appliance51.admin.dialects.yay;

/**
 * Created by yuananyun on 2016/10/26.
 */
 enum PageActionStatus {
    CREATE("create"), EDIT("edit"), REVIEW("review"), OTHER("other");
    private String value;

    PageActionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
