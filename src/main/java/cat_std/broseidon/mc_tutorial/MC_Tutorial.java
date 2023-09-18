package cat_std.broseidon.mc_tutorial;

import cat_std.broseidon.mc_tutorial.commands.CheckConsoleCommand;
import cat_std.broseidon.mc_tutorial.commands.HelloCommand;
import cat_std.broseidon.mc_tutorial.commands.PermissionCommand;
import cat_std.broseidon.mc_tutorial.commands.SayHelloCommand;
import cat_std.broseidon.mc_tutorial.events.MyEventListener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MC_Tutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        getServer().getPluginManager().registerEvents(new MyEventListener(), this);

        getCommand("hello").setExecutor(new HelloCommand()); // Đăng ký lệnh /hello
        getCommand("sayhello").setExecutor(new SayHelloCommand()); // Đăng ký lệnh /sayhello
        getCommand("checkconsole").setExecutor(new CheckConsoleCommand()); // Đăng ký lệnh /checkconsole
        getCommand("permissionCommand").setExecutor(new PermissionCommand()); // Đăng ký lệnh /permissionCommand

        getCommand("sayhello").setTabCompleter(new SayHelloCommand()); // Đăng ký gợi ý cho lệnh /sayhello

        PluginManager pm = getServer().getPluginManager();
        pm.addPermission(new Permission("mc_tutorial.permissionCommand")); // Đăng ký quyền mc_tutorial.permissionCommand
    }

    @Override
    public void onDisable() {
        // Method này sẽ được gọi khi Plugin được tắt
    }
}
