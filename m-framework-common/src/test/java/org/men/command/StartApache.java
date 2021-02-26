package org.men.command;

/**
 * 具体命令 1
 *
 * @author mfc
 * @version v1.0
 * @date 2021/1/25 3:27 下午
 **/
public class StartApache extends ServerCommand {

    public StartApache(Server server) {
        super(server);
    }

    @Override
    public void execute() {
        System.out.println("StartApache:开始启动");
        server.launchCommand("sudo service apache2 start");
    }
}
