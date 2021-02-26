package org.men.command;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/1/25 3:35 下午
 **/
public class CommandTest {

    /**
     * 命令的发起者
     *
     * @param args
     */
    public static void main(String[] args) {
        Administrator admin = new Administrator();
        Server server = new Server();

        admin.setCommand(new StartApache(server));
        admin.typeEnter();

        admin.setCommand(new StartTomcat(server));
        admin.typeEnter();

        int size = server.getExecutedCommands().size();

        System.out.println("size:"+size);

    }
}
