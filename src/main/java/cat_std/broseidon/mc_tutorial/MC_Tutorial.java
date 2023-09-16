package cat_std.broseidon.mc_tutorial;

import cat_std.broseidon.mc_tutorial.events.GoodbyeListener;
import cat_std.broseidon.mc_tutorial.events.WelcomeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MC_Tutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        getServer().getPluginManager().registerEvents(new WelcomeListener(), this);
        getServer().getPluginManager().registerEvents(new GoodbyeListener(), this);
    }

    @Override
    public void onDisable() {
        // Method này sẽ được gọi khi Plugin được tắt
    }
}
