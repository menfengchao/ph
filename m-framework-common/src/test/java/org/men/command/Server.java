package org.men.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 做命令的执行记录
 *
 * @author mfc
 * @version v1.0
 * @date 2021/1/25 3:15 下午
 **/
public class Server {

    //与普通终端一样，我们将执行的命令存储在历史记录中
    private List<String> executedCommands = new ArrayList<>();

    public void launchCommand(String command){
        System.out.println("Executing: "+command+" on server 已记录");
        this.executedCommands.add(command);
    }

    public List<String> getExecutedCommands(){
        return this.executedCommands;
    }

}
