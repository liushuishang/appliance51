package appliance51.dao.model;

/**
 * Created by yuananyun on 2016/10/23.
 */
public class NamingObject {
    private String id;
    private String name;

    public NamingObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
