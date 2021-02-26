package org.men.command;

/**
 * 命令的执行者
 *
 * @author mfc
 * @version v1.0
 * @date 2021/1/25 3:32 下午
 **/
public class Administrator {

    private ServerCommand command;

    public void setCommand(ServerCommand command){
        this.command = command;
    }

    public void typeEnter(){
        this.command.execute();
    }
}
