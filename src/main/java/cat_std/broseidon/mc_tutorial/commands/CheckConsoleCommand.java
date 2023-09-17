package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CheckConsoleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("checkconsole")) {
            if (sender instanceof ConsoleCommandSender) {
                // Người gửi command là console
                sender.sendMessage("Bạn là console.");
            } else if (sender instanceof Player) {
                // Người gửi command là một người chơi
                Player player = (Player) sender;
                sender.sendMessage("Bạn là người chơi với tên: " + player.getName());
            } else {
                // Người gửi không phải là console và cũng không phải là người chơi (ví dụ: command block)
                sender.sendMessage("Bạn là một loại sender khác.");
            }
            return true;
        }
        return false;
    }
}