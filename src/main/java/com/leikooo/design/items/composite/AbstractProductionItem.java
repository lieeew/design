package com.leikooo.design.items.composite;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 抽象角色对应的类 ，组合模式的抽象模式
 */
public abstract class AbstractProductionItem {

    public void addProductChild(AbstractProductionItem item) {
        throw new UnsupportedOperationException("Not support child aad");
    }

    public void delProductionChild(AbstractProductionItem item) {
        throw new UnsupportedOperationException("Not support child del");
    }

}
