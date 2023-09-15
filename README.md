# Hướng Dẫn Bài 2: Sử Dụng Event và Listener trong Minecraft Plugin

> [!NOTE]
> Trong bài này, bạn sẽ học cách sử dụng `Event` (sự kiện) và `Listener` (trình lắng nghe) để tương tác với các sự kiện xảy ra trong Minecraft bằng plugin của bạn. <br> 
> `Event` và `Listener` là một phần quan trọng của việc phát triển plugin, cho phép bạn theo dõi và phản ứng khi xảy ra các sự kiện trong game.

## Bước 1: Chuẩn Bị Môi Trường

> [!NOTE]
> Trước khi bắt đầu, hãy đảm bảo bạn đã cài đặt môi trường phát triển plugin Minecraft và đã tạo một plugin cơ bản theo hướng dẫn trong [Bài 1: Bắt Đầu](https://github.com/leminhbao308/MC-Tutorial/tree/01-create-project).

Tạo một package để lưu trữ các event và listener của bạn. <br> 
Trong bài này, ta sẽ tạo một package tên là `events` để lưu trữ các event và listener. <br>
Để dễ hiểu hơn, hãy xem qua cây thư mục của plugin của bạn:

```
.
├── pom.xml
└── src
    └── main
        ├── java
        │   └── cat_std
        │       └── broseidon
        │           └── mc_tutorial
        │               ├── events                      // Đây là package chứa các event và listener của bạn
        │               │   └── MyEventListener.java    // Đây là listener của bạn
        │               └── MC_Tutorial.java            // Đây là main class của plugin của bạn
        └── resources
            └── plugin.yml
```

## Bước 2: Tạo Một Event Listener

> [!NOTE]
> **Event**: Trong Minecraft, Event (sự kiện) là một sự kiện nào đó xảy ra trong game, ví dụ như người chơi đặt một khối, người chơi kết nối vào máy chủ, hoặc một mob bị giết. Plugin của bạn có thể lắng nghe và phản ứng với các sự kiện này.
>
>**Listener**: Listener (trình lắng nghe) là một phần của plugin của bạn được sử dụng để lắng nghe và xử lý các sự kiện. Một listener phải triển khai interface `Listener` và sử dụng annotation `@EventHandler` để đánh dấu các phương thức xử lý sự kiện.

```java
package cat_std.broseidon.mc_tutorial.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyEventListener implements Listener {

    @EventHandler // Đánh dấu phương thức xử lý sự kiện
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Chào mừng bạn đến với server của Broseidon!"); // Gửi tin nhắn đến người chơi khi họ vào server
    }
}

```

> [!NOTE]
> Trong ví dụ trên, chúng ta đã sử dụng annotation `@EventHandler` để đánh dấu phương thức `onPlayerJoin()` là một phương thức xử lý sự kiện. <br>
> Phương thức `onPlayerJoin()` sẽ được gọi mỗi khi một người chơi kết nối vào máy chủ.
> 
> Bạn có thể tìm hiểu thêm về các sự kiện khác trong Minecraft tại [trang web này](https://bukkit.gamepedia.com/Event_API_Reference).

### Một số Event Phổ Biến

- `PlayerJoinEvent`: Được gọi khi một người chơi kết nối vào máy chủ.
- `PlayerQuitEvent`: Được gọi khi một người chơi ngắt kết nối khỏi máy chủ.
- `PlayerInteractEvent`: Được gọi khi một người chơi tương tác với một khối hoặc một item.
- `PlayerMoveEvent`: Được gọi khi một người chơi di chuyển.
- `AsyncPlayerChatEvent`: Được gọi khi một người chơi gửi một tin nhắn trong chat.
- `BlockBreakEvent`: Được gọi khi một khối bị phá hủy.
- `BlockPlaceEvent`: Được gọi khi một khối được đặt.
- Và nhiều sự kiện khác nữa...

### Tạo Một Event Tùy Chỉnh (Optional)

Nếu bạn muốn tạo một event tùy chỉnh để sử dụng trong plugin của bạn, bạn có thể làm như sau:

```java
package cat_std.broseidon.mc_tutorial.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CustomEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String message;

    public CustomEvent(String example) {
        message = example;
    }

    public String getMessage() {
        return message;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
```

> [!NOTE]
> Trong ví dụ trên, chúng ta đã tạo một event tùy chỉnh có tên là `CustomEvent`. <br>
> Bạn có thể tìm hiểu cách tạo event tùy chỉnh tại [trang web này](https://bukkit.gamepedia.com/Event_API_Reference#Creating_Custom_Events).

## Bước 3: Đăng Ký Listener

Khi bạn đã tạo Listener của mình, bạn cần đăng ký nó để plugin của bạn có thể lắng nghe và phản ứng với các sự kiện.

1. Trong class chính của plugin của bạn (thường là main class), tại phương thức `onEnable()` ta sẽ đăng ký listener của mình.

```java
package cat_std.broseidon.mc_tutorial;

import cat_std.broseidon.mc_tutorial.events.MyEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MC_Tutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        getServer().getPluginManager().registerEvents(new MyEventListener(), this); // Đăng ký listener
    }

    @Override
    public void onDisable() {
        // Method này sẽ được gọi khi Plugin được tắt
    }
}

```

2. Bây giờ listener của bạn đã được đăng ký và sẽ lắng nghe các sự kiện bạn đã xác định trong `MyEventListener`.

> [!NOTE]
> Trong ví dụ trên, chúng ta đã sử dụng phương thức `registerEvents()` để đăng ký listener của mình. <br>
> Bạn có thể tìm hiểu thêm về cách đăng ký listener tại [trang web này](https://bukkit.gamepedia.com/Event_API_Reference#Registering_Events).

## Bước 4: Lắng Nghe Event Tùy Chỉnh (Optional)

Nếu bạn muốn các listener của bạn lắng nghe các event tùy chỉnh, bạn có thể làm như sau:

1. Tạo một class mới để đại diện cho sự kiện của bạn, ví dụ: `CustomEvent.java` mà ta đã tạo ở [thao tác trên](#tạo-một-event-tùy-chỉnh-optional)

2. Trong listener của bạn, bạn có thể tạo và gửi sự kiện này khi điều kiện cụ thể xảy ra.

```java
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
```

## Bước 5: Chạy Plugin và Kiểm Tra

1. Đảm bảo bạn đã sao chép plugin của bạn vào thư mục `plugins` của máy chủ Minecraft.

2. Khởi động lại máy chủ Minecraft của bạn hoặc sử dụng lệnh `/reload` để nạp lại plugin.

3. Thực hiện hành động hoặc điều kiện mà bạn đã xác định trong listener để kiểm tra sự hoạt động của plugin.

## Kết Luận

> Bài hướng dẫn này đã giới thiệu cho bạn cách sử dụng event và listener trong plugin Minecraft của bạn. Sử dụng event và listener là cách mạnh mẽ để tương tác với sự kiện trong game và tạo ra các tính năng tùy chỉnh thú vị. Hãy tiếp tục học và phát triển plugin của bạn! Để biết thêm thông tin về cách sử dụng event và listener, bạn có thể xem thêm [tài liệu chính thức của Bukkit](https://bukkit.gamepedia.com/Event_API_Reference). <br>
> 
> Nếu bạn có bất kì thắc mắc nào, hãy liên hệ với mình qua [Discord](https://discord.com/users/873024375685775361)

> [!IMPORTANT]  
> Bài viết này được viết bởi [Lê Minh Bảo]() <br>
> Hãy cho mình một sao nếu bạn thấy bài viết này hữu ích nhé! <br>
> Xin cảm ơn!
