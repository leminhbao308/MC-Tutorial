package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("hello")) {
            sender.sendMessage("Xin chào!"); // Gửi lời chào khi người chơi sử dụng lệnh /hello
        }
        return true;
    }
}