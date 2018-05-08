package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("item/desc")
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;

    @GetMapping("{id}")
    public ResponseEntity<ItemDesc> getItemDescByItemId(@PathVariable("id") Long id) {
        try {
            if (id == null || id.intValue() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            ItemDesc itemDesc = itemDescService.queryById(id);
            if (itemDesc == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
