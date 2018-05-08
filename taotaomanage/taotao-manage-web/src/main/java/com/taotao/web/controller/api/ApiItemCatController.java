package com.taotao.manage.controller.api;

import com.taotao.common.pojo.ItemCatResult;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@RequestMapping("api/item/cat")
@RestController("api/item/cat")
public class ApiItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping
    public ResponseEntity<ItemCatResult> queryItemCatAll() {
        try {
            ItemCatResult itemCatResult = itemCatService.queryItemCatResult();
            if (itemCatResult == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(itemCatResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
