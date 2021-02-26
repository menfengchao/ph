package org.men.command;

/**
 * 具体命令 2
 *
 * @author mfc
 * @version v1.0
 * @date 2021/1/25 3:30 下午
 **/
public class StartTomcat extends ServerCommand {

    public StartTomcat(Server server) {
        super(server);
    }

    @Override
    public void execute() {
        System.out.println("StartTomcat:开始启动");
        server.launchCommand("sudo service tomcat7 start");
    }
}
