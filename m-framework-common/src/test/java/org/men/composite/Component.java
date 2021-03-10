package org.men.composite;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/3/9 10:15 上午
 **/
public interface Component {

    public void add(Component c);

    public void remove(Component c);

    public Component getChild(int i);

    public void operation();

}
