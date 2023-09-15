package cat_std.broseidon.mc_tutorial.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyEventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Chào mừng bạn đến với server của Broseidon!"); // Gửi tin nhắn đến người chơi khi họ vào server
    }

    @EventHandler
    public void onCustomEvent(CustomEvent event) { // Đây là một ví dụ về việc sử dụng Custom Event
        System.out.println(event.getMessage());
    }
}
