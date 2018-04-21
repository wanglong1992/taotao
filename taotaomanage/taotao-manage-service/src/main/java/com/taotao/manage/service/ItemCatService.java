package com.taotao.manage.service;

import com.taotao.manage.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> querByParentId(ItemCat record);
}
