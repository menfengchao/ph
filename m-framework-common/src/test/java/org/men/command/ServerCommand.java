package org.men.command;

/**
 * 抽象命令
 *
 * @author mfc
 * @version v1.0
 * @date 2021/1/25 3:14 下午
 **/
abstract class ServerCommand {

    protected Server server;

    public ServerCommand(Server server){
        this.server = server;
    }

    public abstract void execute();
}
