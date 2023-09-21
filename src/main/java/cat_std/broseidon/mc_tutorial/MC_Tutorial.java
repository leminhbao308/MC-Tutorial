package cat_std.broseidon.mc_tutorial;

import cat_std.broseidon.mc_tutorial.commands.*;
import cat_std.broseidon.mc_tutorial.events.MyEventListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MC_Tutorial extends JavaPlugin {

    private FileConfiguration config; // <-- Hãy bỏ tệp này
    private ConfigManager configManager; // <-- Thay vào đó, hãy tạo một ConfigManager

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        // genConfig();  <-- Hãy bỏ method này
        configManager = new ConfigManager(getConfig()); // <-- Thay vào đó, hãy tạo một ConfigManager

        getServer().getPluginManager().registerEvents(new MyEventListener(), this);

        getCommand("hello").setExecutor(new HelloCommand()); // Đăng ký lệnh /hello
        getCommand("sayhello").setExecutor(new SayHelloCommand()); // Đăng ký lệnh /sayhello
        getCommand("checkconsole").setExecutor(new CheckConsoleCommand()); // Đăng ký lệnh /checkconsole
        getCommand("permissionCommand").setExecutor(new PermissionCommand()); // Đăng ký lệnh /permissionCommand

        getCommand("sayhello").setTabCompleter(new SayHelloCommand()); // Đăng ký gợi ý cho lệnh /sayhello

        getCommand("test").setExecutor(new CommandTest(configManager)); // Đăng ký lệnh /test

        PluginManager pm = getServer().getPluginManager();
        pm.addPermission(new Permission("mc_tutorial.permissionCommand")); // Đăng ký quyền mc_tutorial.permissionCommand
    }

    @Override
    public void onDisable() {
        // Method này sẽ được gọi khi Plugin được tắt
    }

    public void genConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        config = getConfig();
    }
}
