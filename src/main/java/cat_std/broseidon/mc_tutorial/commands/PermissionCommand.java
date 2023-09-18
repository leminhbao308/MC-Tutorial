package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PermissionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("mc_tutorial.permissionCommand")) {
                // Thực hiện lệnh của bạn ở đây
                return true;
            } else {
                player.sendMessage("Bạn không có quyền sử dụng lệnh này.");
                return false;
            }
        }
        // Xử lý trường hợp lệnh được gọi từ console
        return true;
    }
}
