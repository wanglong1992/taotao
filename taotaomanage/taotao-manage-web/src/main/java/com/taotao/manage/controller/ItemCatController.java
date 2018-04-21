package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taotao.manage.controller.vo.ItemCatVO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("item")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("cat")
    public ResponseEntity<List<ItemCatVO>> getItemCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        try {
            ItemCat record = new ItemCat();
            record.setParentId(parentId);
            record.setStatus(1);
            List<ItemCat> itemCats = itemCatService.querByParentId(record);
            if (CollectionUtils.isEmpty(itemCats)) {
                return ResponseEntity.notFound().build();
            }
            List<ItemCatVO> itemCatVOList = new ArrayList<>();
            for (ItemCat itemCat : itemCats) {
                ItemCatVO itemCatVO = new ItemCatVO();
                BeanUtils.copyProperties(itemCat,itemCatVO);
                itemCatVOList.add(itemCatVO);
                System.out.println(itemCat+":"+itemCatVO);
            }
            return ResponseEntity.ok(itemCatVOList);
        } catch (BeansException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
