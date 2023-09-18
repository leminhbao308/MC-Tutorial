# MC TUTORIAL 03: COMMANDS AND PERMISSIONS <br> (Part 2)

> [!NOTE]
> Trong bài này, bạn sẽ học cách sử dụng Tab Completion để cải thiện sự tiện dụng cho command. <br>
> Bên cạnh đó, bạn sẽ học được cách sử dụng permissions để kiểm soát quyền sử dụng của người chơi lên các command của bạn. <br>

## Tab Completion

Để tạo một Tab Completion cho command của bạn, bạn cần override phương thức `onTabComplete()` từ class `TabCompleter`<br>
Hãy xem qua ví dụ về cách thiết lập sau:

```java
package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SayHelloCommand implements CommandExecutor, TabCompleter {

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
        return null;
    }
}
```

> [!NOTE]
> Tab Completion sẽ rất hữu ích nếu command của bạn cần nhiều tham số. <br>
> Nó sẽ giúp người chơi dễ dàng hơn trong việc nhập các tham số cho command của bạn.


Với ví dụ trên, tôi sẽ hướng dẫn bạn cách để thêm tên của người chơi vào Tab Completion. <br>
Để làm được điều này, bạn cần sử dụng phương thức `getOnlinePlayers()` của class `Bukkit` để lấy danh sách các người chơi đang online. <br>
Sau đó, bạn sẽ duyệt qua danh sách này và thêm tên của từng người chơi vào danh sách gợi ý. <br>
Hãy xem qua ví dụ sau:

```java
package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SayHelloCommand implements CommandExecutor, TabCompleter {
    // CommandExecutor đã được giới thiệu trong bài hướng dẫn trước
    // {...}
    
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
```
Như vậy, bạn đã tạo thành công một Tab Completion cho command của bạn. <br>
Hãy thử build và chạy plugin của bạn để kiểm tra kết quả nhé! <br>

> [!NOTE]
> Một số Tab Completion mẫu mã bạn có thể tham khảo:
> **[Xem tại đây](https://gist.github.com/leminhbao308/7774850a4c5d26146f60bbfa3d636598)**

### Đăng ký Tab Completion

Bạn cần phải đăng ký Tab Completion cho command của bạn trong phương thức `onEnable()` của main class của bạn. <br>
Để làm được điều này, bạn cần sử dụng API của plugin để đăng ký Tab Completion. <br>

```java
getCommand("sayhello").setTabCompleter(new SayHelloCommand()); // Đăng ký gợi ý cho lệnh /sayhello
```

> [!NOTE]
> Đối với các lệnh chỉ được gọi từ console, bạn có thể bỏ qua bước đăng ký Tab Completion. <br>

## Permissions

> [!IMPORTANT]
> **Permission** (quyền hạn) là một phần quan trọng của việc quản lý truy cập và sử dụng các lệnh và tính năng trong plugin Minecraft. <br> 
> Nó cho phép bạn kiểm soát những người chơi nào có quyền sử dụng một lệnh cụ thể hoặc tính năng trong game.

### Các bước cài đặt permission cho lệnh

Để cài đặt permission cho một lệnh trong plugin của bạn, bạn cần thực hiện các bước sau:

1. **Xác định Permission**: Đầu tiên, bạn cần xác định quyền hạn mà bạn muốn gán cho lệnh của mình. Ví dụ, nếu bạn muốn tạo lệnh `/mycommand`, bạn có thể muốn xác định quyền hạn như `myplugin.mycommand`. Quyền hạn này sẽ được sử dụng để kiểm tra xem một người chơi có quyền sử dụng lệnh này hay không.

2. **Đăng Ký Permission**: Trong plugin của bạn, bạn cần đăng ký quyền hạn này. Điều này thường được thực hiện trong phương thức `onEnable()` của main class của bạn. Sử dụng API của plugin để đăng ký permission. Ví dụ:

```java
PluginManager pm = getServer().getPluginManager();
pm.addPermission(new Permission("mc_tutorial.permissionCommand"));
```

3. **Liên kết Permission với Lệnh**: Trong phần triển khai lệnh của bạn, bạn cần kiểm tra xem người chơi đã được cấp quyền hạn này trước khi cho phép họ sử dụng lệnh. Bạn có thể làm điều này bằng cách sử dụng API của plugin để kiểm tra quyền hạn của người chơi. Ví dụ:

```java
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
        Player player = (Player) sender;
        
        if (player.hasPermission("myplugin.mycommand")) {
            // Thực hiện lệnh của bạn ở đây
            return true;
        } else {
            player.sendMessage("Bạn không có quyền sử dụng lệnh này.");
            return false;
        }
    }
    // Xử lý trường hợp lệnh được gọi từ console
    return true;
}
```

> [!NOTE]
> Đối với các lệnh được gọi từ console, bạn có thể bỏ qua bước kiểm tra quyền hạn của người chơi. <br>
> Tuy nhiên bạn có thể chặn console sử dụng lệnh theo như hướng dẫn ở [bài viết trước đó](https://github.com/leminhbao308/MC-Tutorial/tree/03a-command-and-permission#command-tr%C3%AAn-console).

### Tổng kết

Permission (quyền hạn) là một phần quan trọng của việc kiểm soát truy cập và sử dụng các lệnh và tính năng trong plugin Minecraft của bạn. <br>
Bằng cách cài đặt permission cho lệnh của bạn và kiểm tra quyền hạn của người chơi, bạn có thể tạo ra một hệ thống quản lý quyền linh hoạt trong server Minecraft của mình khi
kết hợp với các plugin quản lý quyền hạn khác như [LuckPerms](https://luckperms.net/).

## Kết Luận

> Trong bài hướng dẫn này, bạn đã học cách sử dụng Tab Completion để cải thiện sự tiện dụng cho command. <br>
> Bên cạnh đó, bạn đã học được cách sử dụng permissions để kiểm soát quyền sử dụng của người chơi lên các command của bạn.
> 
> Nếu bạn gặp bất kỳ vấn đề nào hoặc có câu hỏi, đừng ngần ngại đặt câu hỏi tại trang [Discussion của dự án](https://github.com/leminhbao308/MC-Tutorial/discussions) để nhận sự trợ giúp từ cộng đồng. 
> Bạn cũng có thể liên hệ với tôi qua [Discord cá nhân](https://discord.com/users/873024375685775361).
> 
> Chúc bạn thành công trong việc phát triển plugin Minecraft của mình!

> [!IMPORTANT]  
> Bài viết này được viết bởi [Lê Minh Bảo]() <br>
> Hãy cho mình một sao nếu bạn thấy bài viết này hữu ích nhé! <br>
> Xin cảm ơn!