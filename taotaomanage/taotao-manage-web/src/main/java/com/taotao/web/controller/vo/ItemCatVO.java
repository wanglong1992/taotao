package com.taotao.manage.controller.vo;

import com.taotao.manage.pojo.ItemCat;

public class ItemCatVO extends ItemCat {
    public String getText() {
        return getName();
    }

    public String getState() {
        return getIsParent() ? "closed" : "open";
    }
}
