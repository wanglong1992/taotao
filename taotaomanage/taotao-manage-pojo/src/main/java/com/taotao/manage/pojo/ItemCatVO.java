package com.taotao.manage.pojo;

public class ItemCatVO extends ItemCat {
    public String getText() {
        return getName();
    }

    public String getStatu() {
        return getIsParent() ? "closed" : "open";
    }
}
