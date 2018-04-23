package com.taotao.manage.service;

import com.github.abel533.mapper.Mapper;
import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.mapper.ItemMapper;
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
        try {
            Integer count1 = this.save(item);
            System.err.println("id" + item.getId());
            ItemDesc itemDesc = new ItemDesc();
            itemDesc.setItemDesc(desc);
            itemDesc.setItemId(item.getId());
            Integer count2 = itemDescService.save(itemDesc);
            return count1 == 1 && count2 == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
