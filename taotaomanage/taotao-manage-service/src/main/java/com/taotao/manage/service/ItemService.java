package com.taotao.manage.service;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemDescService itemDescService;

    public Boolean saveItemAndDesc(Item item, String desc) {
        //如果desc没有则保存 空
        //初始化item
        item.setId(null);
        item.setStatus(1);
        Integer count1 = this.save(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        Integer count2 = itemDescService.save(itemDesc);
        return count1 == 1 && count2 == 1;
    }
}
