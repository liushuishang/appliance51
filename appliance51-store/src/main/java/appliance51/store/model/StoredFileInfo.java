package appliance51.store.model;

/**
 * Created by yuananyun on 2016/10/16.
 */
public class StoredFileInfo {
    public String key;
    public String hash;
    public long fsize;
    public long putTime;
    public String mimeType;
    public String endUser;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getFsize() {
        return fsize;
    }

    public void setFsize(long fsize) {
        this.fsize = fsize;
    }

    public long getPutTime() {
        return putTime;
    }

    public void setPutTime(long putTime) {
        this.putTime = putTime;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getEndUser() {
        return endUser;
    }

    public void setEndUser(String endUser) {
        this.endUser = endUser;
    }
}
