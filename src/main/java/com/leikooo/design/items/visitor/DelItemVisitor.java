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
public class DelItemVisitor implements ItemsVisitor<AbstractProductionItem> {

    @Resource
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public AbstractProductionItem visitor(AbstractProductionItem productionItem) {
        ProductComposite currentItem = (ProductComposite) redisCommonProcessor.get("items");
        ProductComposite delTem = (ProductComposite) productionItem;
        if (delTem.getId() == currentItem.getId()) {
            throw new UnsupportedOperationException("根节点不能删除");
        }
        // 如果要删除的父节点是当前节点
        if (delTem.getPid() == currentItem.getId()) {
            currentItem.delProductionChild(delTem);
        }
        // 通过 delChild 方法递归删除类目位置
        delItem(delTem, currentItem);
        return currentItem;
    }

    private void delItem(ProductComposite delItem, ProductComposite currentItem) {
        for (AbstractProductionItem items : currentItem.getChildren()) {
           if (((ProductComposite) items).getPid() == delItem.getPid()) {
               items.delProductionChild(delItem);
               break;
           } else {
               delItem(delItem, (ProductComposite) items);
           }
        }
    }

}
