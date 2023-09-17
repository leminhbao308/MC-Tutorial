package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SayHelloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("sayhello")) {
            if (args.length == 0) {
                sender.sendMessage("Vui lòng cung cấp tên của bạn!"); // Gửi thông báo nếu không có tham số
            } else {
                String playerName = args[0];
                sender.sendMessage("Xin chào, " + playerName + " từ plugin của bạn!"); // Gửi tin nhắn với tham số
            }
        }
        return true;
    }
}