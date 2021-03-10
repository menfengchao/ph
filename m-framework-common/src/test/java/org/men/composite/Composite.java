package org.men.composite;

import java.util.ArrayList;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/3/9 10:18 上午
 **/
public class Composite implements Component{

    private ArrayList<Component> children = new ArrayList<Component>();

    @Override
    public void add(Component c) {
        children.add(c);
    }

    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        for (Object obj : children) {
            ((Component) obj).operation();
        }
    }
}
