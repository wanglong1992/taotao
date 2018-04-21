package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("item")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("cat")
    public ResponseEntity<List<ItemCat>> getItemCatList(@RequestParam ("id")Long id) {

        return null;
    }
}
