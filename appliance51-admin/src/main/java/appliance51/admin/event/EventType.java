package appliance51.admin.event;

/**表示一个事件类型
 * Created by yuananyun on 2016/10/23.
 */
public enum EventType {
    BEFORE_CREATE_FORM("beforeCreateForm"),
    BEFORE_DELETED("beforeDeleted"),
    BEFORE_EDIT_FORM("beforeEditForm");

    private String identification;

    EventType(String identification) {
        this.identification = identification;
    }

    public String getValue(){
        return identification;
    }
}
