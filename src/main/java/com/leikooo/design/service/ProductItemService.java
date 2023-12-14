package com.leikooo.design.service;

import com.leikooo.design.items.composite.AbstractProductionItem;
import com.leikooo.design.items.composite.ProductComposite;
import com.leikooo.design.items.visitor.AddItemVisitor;
import com.leikooo.design.items.visitor.DelItemVisitor;
import com.leikooo.design.pojo.ProductItem;
import com.leikooo.design.repo.ProductItemRepository;
import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Service
@Transactional // 事务注解
public class ProductItemService {
    @Resource
    private RedisCommonProcessor redisCommonProcessor;
    @Resource
    private AddItemVisitor addItemVisitor;
    @Resource
    private DelItemVisitor delItemVisitor;
    @Resource
    private ProductItemRepository productItemRepository;

    public ProductComposite fetchAllItems() {
        Object item = redisCommonProcessor.get("items");
        if (item != null) {
            return (ProductComposite) item;
        }
        List<ProductItem> fetchDbItems = productItemRepository.findAll();
        ProductComposite items = generateProductTree(fetchDbItems);
        if (items == null) {
            throw new UnsupportedOperationException("Product items should not be null in DB!");
        }
        redisCommonProcessor.set("items", items);
        return items;
    }

    private ProductComposite generateProductTree(List<ProductItem> fetchDbItems) {
        List<ProductComposite> productCompositeList = new ArrayList<>(fetchDbItems.size());
        fetchDbItems.forEach((item) -> productCompositeList.add(ProductComposite.builder().id(item.getId()).name(item.getName()).pid(item.getPid()).build()));
        // pid    ----   List<ProductComposite>
        Map<Integer, List<ProductComposite>> groupingList = productCompositeList.stream().collect(Collectors.groupingBy(ProductComposite::getPid));
        productCompositeList.forEach((item) -> {
            List<ProductComposite> list = groupingList.get(item.getId());
            item.setChildren(list == null ? new ArrayList<>() : list.stream().map(x -> (AbstractProductionItem) x).collect(Collectors.toList()));
        });
        return productCompositeList.size() == 0 ? null : productCompositeList.get(0);
    }

    public ProductComposite addItems(ProductItem item) {
        // 先更新数据库
        productItemRepository.addItem(item.getName(), item.getPid());
        // 调用 visitor 实现，前端显示的树形结构
        // 传入的 item 没有 id ，需要查询数据库获取到 id
        ProductComposite addItem = ProductComposite.builder().pid(item.getPid()).name(item.getName()).id(productItemRepository.findByNameAndPid(item.getName(), item.getPid()).getId()).build();
        AbstractProductionItem updateItems = addItemVisitor.visitor(addItem);
        redisCommonProcessor.set("items", updateItems);
        return (ProductComposite) updateItems;
    }

    public ProductComposite delItems(ProductItem item) {
        // 先更新数据库
        productItemRepository.delItem(item.getId(), item.getPid());
        ProductComposite delItem = ProductComposite.builder().id(item.getId()).name(item.getName()).pid(item.getPid()).build();
        AbstractProductionItem updateItem = delItemVisitor.visitor(delItem);
        redisCommonProcessor.set("items", updateItem);
        return (ProductComposite) updateItem;
    }
}
