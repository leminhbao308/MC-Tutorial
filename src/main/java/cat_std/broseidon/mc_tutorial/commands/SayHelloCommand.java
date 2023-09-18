package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SayHelloCommand implements TabExecutor {

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

    /**
     * Đây là phương thức được gọi khi người dùng gõ lệnh
     * @param commandSender là người gửi lệnh
     * @param command là lệnh
     * @param s là tên lệnh
     * @param strings là các tham số
     * @return danh sách các tham số có thể gợi ý
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (s.equalsIgnoreCase("sayhello")) {
            if (strings.length == 1) {
                List<String> playerNames = new ArrayList<>(); // Danh sách các tên người chơi
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerNames.add(player.getName()); // Thêm tên của người chơi vào danh sách
                }
                return playerNames; // Trả về danh sách các tên người chơi
            }
        }
        return null;
    }
}