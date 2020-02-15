package edu.sandau.entity;

import java.util.List;
import java.util.Map;

public class Page {

    private Integer pageNo ;

    private Integer pageSize ;

    private Integer total;

    private Map<String, Object> option;

    private List<?> rows;

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public void setOption(Map<String, Object> option) {
        this.option = option;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public Map<String, Object> getOption() {
        return option;
    }

    public List<?> getRows() {
        return rows;
    }
}
