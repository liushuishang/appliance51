package appliance51.admin.model;

import java.util.List;

/**
 * Created by yuananyun on 2016/10/7.
 */
public class QueryResult<T> {
    private List<T> rows;
    private long totalPages;
    private long total;

    public QueryResult(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
