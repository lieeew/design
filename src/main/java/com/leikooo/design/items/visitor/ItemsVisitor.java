package com.leikooo.design.items.visitor;

import com.leikooo.design.items.composite.AbstractProductionItem;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public interface ItemsVisitor<T> {

    T  visitor(AbstractProductionItem productionItem);
}
