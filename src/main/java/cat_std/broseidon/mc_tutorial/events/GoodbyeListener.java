package cat_std.broseidon.mc_tutorial.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GoodbyeListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.getPlayer().sendMessage("Hẹn gặp lại bạn lần sau!"); // Gửi tin nhắn đến người chơi khi họ thoát khỏi server
    }
}