package org.men.composite;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/3/9 10:16 上午
 **/
public class Leaf implements Component {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println("我是叶子节点"+name);
    }
}
