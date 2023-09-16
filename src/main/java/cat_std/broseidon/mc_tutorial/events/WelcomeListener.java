package cat_std.broseidon.mc_tutorial.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WelcomeListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Chào mừng "+event.getPlayer().getName()+" đến với server!"); // Gửi tin nhắn đến người chơi khi họ vào server
    }
}
