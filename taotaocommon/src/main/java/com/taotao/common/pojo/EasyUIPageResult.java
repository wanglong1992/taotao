package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUIPageResult<T> implements Serializable {
    private static final long serialVersionUID = -8151146539612663947L;
    private int total;

    public EasyUIPageResult() {
    }

    public EasyUIPageResult(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public EasyUIPageResult<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public EasyUIPageResult<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }
}
