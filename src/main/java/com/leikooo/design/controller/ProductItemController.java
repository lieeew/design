package com.leikooo.design.controller;

import com.leikooo.design.items.composite.ProductComposite;
import com.leikooo.design.pojo.ProductItem;
import com.leikooo.design.service.ProductItemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@RestController
public class ProductItemController {
    @Resource
    private ProductItemService productItemService;

    @PostMapping("/fetchAllItem")
    public ProductComposite fetchAllItems() {
        return productItemService.fetchAllItems();
    }

    @PostMapping("/addItems")
    public ProductComposite addItem(@RequestBody ProductItem item) {
        return productItemService.addItems(item);
    }
    @PostMapping("/delItems")
    public ProductComposite delItem(@RequestBody ProductItem item) {
        return productItemService.delItems(item);
    }
}
