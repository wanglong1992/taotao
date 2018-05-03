package com.taotao.common.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ItemCatData implements Serializable {
    private static final long serialVersionUID = -2551126061851263043L;
    @JsonProperty("u")
    private String url;
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    private List<?> item;

    public String getUrl() {
        return url;
    }

    public ItemCatData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemCatData setName(String name) {
        this.name = name;
        return this;
    }

    public List<?> getItem() {
        return item;
    }

    public ItemCatData setItem(List<?> item) {
        this.item = item;
        return this;
    }
}
