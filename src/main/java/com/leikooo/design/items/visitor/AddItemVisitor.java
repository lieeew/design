package com.leikooo.design.items.visitor;

import com.leikooo.design.items.composite.AbstractProductionItem;
import com.leikooo.design.items.composite.ProductComposite;
import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class AddItemVisitor implements ItemsVisitor<AbstractProductionItem> {
    @Resource
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public AbstractProductionItem visitor(AbstractProductionItem productionItem) {
        ProductComposite currentItem = (ProductComposite) redisCommonProcessor.get("items");
        ProductComposite addItem = (ProductComposite) productionItem;
        // 如果新增节点的父节点是当前节点, 则直接添加
        if (addItem.getPid() == currentItem.getPid()) {
            currentItem.addProductChild(addItem);
            return currentItem;
        }
        // 不是当前父节点的元素
        addChild(addItem, currentItem);
        return currentItem;
    }

    private void addChild(ProductComposite addItem, ProductComposite currentItem) {
        for (AbstractProductionItem itemChild : currentItem.getChildren()) {
            if (addItem.getPid() == ((ProductComposite) itemChild).getPid()) {
                currentItem.addProductChild(addItem);
                break;
            } else {
                addChild(addItem, (ProductComposite) itemChild);
            }
        }
    }
}
