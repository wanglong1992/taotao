package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemCatResult implements Serializable {
    private static final long serialVersionUID = 8866757028084431490L;
    //    @JsonProperty(value = "data")
    private List<ItemCatData> data = new ArrayList<>();

    public List<ItemCatData> getData() {
        return data;
    }

    public ItemCatResult setData(List<ItemCatData> data) {
        this.data = data;
        return this;
    }
}
