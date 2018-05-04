package com.taotao.manage.service;

import com.taotao.common.pojo.ItemCatData;
import com.taotao.common.pojo.ItemCatResult;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemCatService extends BaseService<ItemCat> {
    public ItemCatResult queryItemCatResult() {
        ItemCatResult itemCatResult = new ItemCatResult();
        //查出所有数据
        List<ItemCat> itemCats = this.queryAll();
        HashMap<Long, List<ItemCat>> itemCatMap = new HashMap<>();
        //把所有数据封装成已parendId为key,以子类集合为value的map
        for (ItemCat itemCat : itemCats) {
            if (!itemCatMap.containsKey(itemCat.getParentId())) {
                itemCatMap.put(itemCat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }
        //封装一级类目:获取parentId为0的商品类目
        List<ItemCat> itemCatsLevel1 = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatsLevel1) {
            // 创建一级类目对象
            ItemCatData itemCatData = new ItemCatData();
            // 把一级类目添加到result中
            itemCatResult.getData().add(itemCatData);
            // 一级类目的name
            itemCatData.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
            // 一级类目的url
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");

            //二级类目的itemcat数据
            List<ItemCat> itemCatsLevel2 = itemCatMap.get(itemCat.getId());
            // 创建二级类目（ItemCatData）数组
            ArrayList<ItemCatData> itemCatData1 = new ArrayList<>();
            // 设置到所属一级类目
            itemCatData.setItem(itemCatData1);
            for (ItemCat cat : itemCatsLevel2) {
                ItemCatData itemCatData2 = new ItemCatData();
                itemCatData1.add(itemCatData2);
                itemCatData2.setName("<a href='/products/" + cat.getId() + ".html'>" + cat.getName() + "</a>");
                itemCatData2.setUrl("/products/" + cat.getId() + ".html");
                ArrayList<String> strings = new ArrayList<>();
                itemCatData2.setItem(strings);
                List<ItemCat> itemCatsLevel3 = itemCatMap.get(cat.getId());
                for (ItemCat itemCat1 : itemCatsLevel3) {
                    strings.add("/products/" + itemCat1.getId() + ".html|" + itemCat1.getName());
                }
            }
        }
        return itemCatResult;
    }
}
