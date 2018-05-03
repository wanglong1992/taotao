package com.taotao.manage.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIPageResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(Item item, @RequestParam("desc") String desc) {
        if (item == null || StringUtils.isEmpty(item.getTitle())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Boolean b = itemService.saveItemAndDesc(item, desc);
            if (b) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<EasyUIPageResult<Item>> queryItemListByPage(@RequestParam(value = "page", defaultValue = "1") Integer pageNum, @RequestParam(value = "rows", defaultValue = "30") Integer pageSize) {
        try {
            PageInfo<Item> pageInfo = this.itemService.queryPageListOrderBy("updated desc", pageNum, pageSize);
            if (CollectionUtils.isEmpty(pageInfo.getList())) {
                return ResponseEntity.ok(null);
            }
            return ResponseEntity.ok(new EasyUIPageResult<Item>(new Long(pageInfo.getTotal()).intValue(), pageInfo.getList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(Item item, @RequestParam("desc") String desc) {

        try {
            boolean result = itemService.updateItemAndDesc(item, desc);
            if (result) {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

