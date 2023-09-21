package cat_std.broseidon.mc_tutorial.commands;

import cat_std.broseidon.mc_tutorial.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {

    private final ConfigManager configManager;

    public CommandTest(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(configManager.getString("string"));
            player.sendMessage(String.valueOf(configManager.getBoolean("boolean")));
            player.sendMessage(String.valueOf(configManager.getInt("integer")));
            player.sendMessage(String.valueOf(configManager.getStringList("array")));
            player.sendMessage(String.valueOf(configManager.getMaterialList("materials")));
            player.sendMessage(configManager.getInnerString());
        }
        return true;
    }
}