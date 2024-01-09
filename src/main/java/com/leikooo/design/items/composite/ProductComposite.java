package com.leikooo.design.items.composite;

import com.leikooo.design.pojo.ProductItem;
import lombok.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 抽象类 AbstractProductionItem 对应的具体的类, 也就是组合模式的 leaf
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductComposite extends AbstractProductionItem {
    private int id;

    private int pid;

    private String name;

    private List<AbstractProductionItem> children = new ArrayList<>();
    @Override
    public void addProductChild(AbstractProductionItem item) {
        this.children.add(item);
    }

    @Override
    public void delProductionChild(AbstractProductionItem item) {
        ProductComposite composite = (ProductComposite) item;
        Iterator<AbstractProductionItem> iterator = children.iterator();
        while (iterator.hasNext()) {
            ProductComposite next = (ProductComposite) iterator.next();
            // 移除相同 id 的商品
            if (next.getId() == composite.getId()) {
                iterator.remove();
                return;
            }
        }
    }
}
