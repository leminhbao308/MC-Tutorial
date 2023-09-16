# Mini Project: Welcome và Goodbye Message trong Minecraft Plugin

> [!NOTE]
> Trong dự án này, bạn sẽ tạo một plugin Minecraft nhỏ để hiển thị thông điệp "Chào mừng bạn đến với server!" khi người chơi tham gia và "Hẹn gặp lại bạn lần sau!" khi người chơi thoát khỏi server.

## Bước 1: Chuẩn Bị

> [!NOTE]  
> Để bắt đầu, đảm bảo bạn đã tạo một plugin cơ bản theo hướng dẫn trong [Bài 1: Bắt Đầu](https://github.com/leminhbao308/MC-Tutorial/tree/01-create-project).
> 
> Ngoài ra, bạn cũng cần phải có kiến thức cơ bản về [Event và Listener](https://github.com/leminhbao308/MC-Tutorial/tree/02-event-and-listener).

### Tạo các class Event

Trong package `events` ta đã tạo từ trước, tạo các class sau:

```plaintext
.
├── pom.xml
└── src
    └── main
        ├── java
        │   └── cat_std
        │       └── broseidon
        │           └── mc_tutorial
        │               ├── events                      
        │               │   ├── WelcomeListener.java  // Đây là class để xử lý sự kiện khi người chơi tham gia vào server
        │               │   └── GoodbyeListener.java  // Đây là class để xử lý sự kiện khi người chơi thoát khỏi server
        │               └── MC_Tutorial.java
        └── resources
            └── plugin.yml
```

## Bước 2: Tạo Event Listener

> [!NOTE]
> ### Kiến thức đã biết:
> **Event**: Trong Minecraft, Event (sự kiện) là một sự kiện nào đó xảy ra trong game, ví dụ như người chơi đặt một khối, người chơi kết nối vào máy chủ, hoặc một mob bị giết. Plugin của bạn có thể lắng nghe và phản ứng với các sự kiện này.  
> **Listener**: Listener (trình lắng nghe) là một phần của plugin của bạn được sử dụng để lắng nghe và xử lý các sự kiện. Một listener phải triển khai interface `Listener` và sử dụng annotation `@EventHandler` để đánh dấu các phương thức xử lý sự kiện.

### Tạo Listener Cho Welcome Message

1. Tạo một class mới trong package `events` để đại diện cho listener của bạn, ví dụ: `WelcomeListener.java`.

```java
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
```

### Tạo Listener Cho Goodbye Message

2. Tạo một class mới trong package `events` để đại diện cho listener của bạn, ví dụ: `GoodbyeListener.java`.

```java
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
```

> [!WARNING]
> ### Câu Hỏi & Trả Lời (Q&A)
>
> #### Câu hỏi: Tại sao chúng ta cần tách các Listener thành các class riêng biệt?
>
> **Trả lời:** Tách các Listener thành các class riêng biệt là một thực hành quan trọng trong phát triển plugin Minecraft với event và listener. Dưới đây là một số lý do tại sao:
>
> 1. **Tính Tách Biệt Các Chức Năng:** Mỗi Listener thường xử lý một loại sự kiện cụ thể. Bằng cách tách biệt chúng thành các class riêng biệt, bạn giữ cho mã nguồn dễ quản lý hơn và tạo sự rõ ràng giữa các chức năng khác nhau của plugin.
>
> 2. **Dễ Bảo Trì và Sửa Lỗi:** Khi có lỗi xảy ra hoặc bạn cần cập nhật logic cho một loại sự kiện, bạn có thể tìm và chỉnh sửa class Listener tương ứng mà không ảnh hưởng đến các sự kiện khác.
>
> 3. **Sáng Tạo và Mở Rộng:** Bằng cách tạo các class Listener riêng biệt, bạn dễ dàng mở rộng plugin của bạn bằng cách thêm các sự kiện và tính năng mới mà không cần thay đổi quá nhiều code hiện tại.
>
> 4. **Tăng Tính Module:** Tách các Listener thành các class riêng biệt giúp bạn duy trì tính module trong code của bạn. Điều này có nghĩa là bạn có thể kích hoạt hoặc vô hiệu hóa các tính năng cụ thể bằng cách chỉ cần thêm hoặc xóa các class Listener mà không ảnh hưởng đến phần còn lại của plugin.
>
> 5. **Hiệu Suất Tốt Hơn:** Khi bạn tách các Listener ra khỏi một class chung lớn, Minecraft sẽ dễ dàng quản lý và triển khai sự kiện, cải thiện hiệu suất tổng thể của máy chủ.
>
> Vì vậy, tách các Listener thành các class riêng biệt không chỉ là một phong cách lập trình hay, mà còn giúp làm cho plugin của bạn dễ dàng quản lý, mở rộng và bảo trì hơn.

## Bước 3: Đăng Ký Listener

Khi bạn đã tạo Listener của mình, bạn cần đăng ký nó để plugin của bạn có thể lắng nghe và phản ứng với các sự kiện.

1. Trong class chính của plugin của bạn (thường là main class, ví dụ: `MC_Tutorial.java`), tại phương thức `onEnable()`, ta sẽ đăng ký các listener của mình.

```java
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

```

## Bước 4: Build Plugin

1. Đảm bảo bạn đã cài đặt Maven và JDK 8.
2. Mở terminal và chạy lệnh sau để build plugin của bạn:

```bash
mvn clean package
```

3. Nếu build thành công, bạn sẽ thấy thông báo `BUILD SUCCESS` trong terminal và file `.jar` của bạn sẽ được tạo ra trong thư mục `target` của project.
4. Hoặc bạn có thể tải về plugin tôi đã build sẵn [tại đường dẫn này](https://github.com/leminhbao308/MC-Tutorial/blob/02-welcome-goodbye-plugin/target/MC_Tutorial-1.0.0.jar).

## Bước 5: Chạy Plugin và Kiểm Tra

1. Đảm bảo bạn đã sao chép plugin của bạn vào thư mục `plugins` của máy chủ Minecraft.

2. Khởi động lại máy chủ Minecraft của bạn hoặc sử dụng lệnh `/reload` để nạp lại plugin.

3. Khi một người chơi tham gia vào hoặc rời khỏi máy chủ, plugin của bạn sẽ gửi các thông điệp welcome và goodbye tới họ.

## Kết Luận

> [!NOTE]
> Bài viết này đã giới thiệu cho bạn cách sử dụng event và listener trong plugin Minecraft của bạn để tạo các tính năng tùy chỉnh như welcome và goodbye message. Các bước quy trình từ việc chuẩn bị kiến thức, tạo sự kiện cho đến đăng ký listener đã được thực hiện.

Các điểm chính đã học trong bài này:

- Sử dụng Event để lắng nghe các sự kiện trong Minecraft.
- Tạo Listener để xử lý các sự kiện và phản ứng tới chúng.
- Đăng ký Listener trong plugin của bạn để kích hoạt các sự kiện.

> [!NOTE]
> Tiếp tục nghiên cứu và phát triển plugin của bạn. Có rất nhiều sự kiện khác trong Minecraft mà bạn có thể lắng nghe và sử dụng để tạo ra các tính năng tùy chỉnh thú vị. 
> 
>Hãy xem thêm [tài liệu chính thức của Bukkit tại đây](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/package-summary.html) để tìm hiểu thêm về event và listener.

Nếu bạn gặp bất kỳ khó khăn hoặc có bất kỳ câu hỏi nào, đừng ngần ngại liên hệ với tôi qua [Discord](https://discord.com/users/873024375685775361).

Cảm ơn bạn đã tham gia bài học này và chúc bạn thành công trong việc phát triển plugin Minecraft của bạn!

> [!IMPORTANT]  
> Bài viết này được viết bởi [Lê Minh Bảo]() <br>
> Hãy cho mình một sao nếu bạn thấy bài viết này hữu ích nhé! <br>
> Xin cảm ơn!
