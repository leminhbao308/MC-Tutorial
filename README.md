# MC TUTORIAL 03: COMMANDS AND PERMISSIONS (Part 1)

> [!NOTE]
> Trong bài này, bạn sẽ học cách tạo và quản lý các command cho plugin Minecraft của mình. <br>
> Command cho phép bạn tương tác với plugin qua các lệnh sẽ được sử dụng trong game hoặc từ console.

## Một Command Đơn Giản

Để tạo một command cơ bản cho plugin của bạn, bạn cần thực hiện các bước sau:

1. **Tạo Một Class Cho Command**: Tạo một class để đại diện cho command của bạn. 

> [!NOTE]
> Bạn nên đặt tên class theo tên của command để dễ dàng quản lý và tìm kiếm. <br>
> Ví dụ, nếu bạn muốn tạo command `/hello`, bạn có thể tạo một class có tên là `HelloCommand`.
> 
> Tương tự như Event, hãy tạo một package riêng cho các command của bạn để dễ dàng quản lý. <br>
> Trong ví dụ này, tôi sẽ tạo một package có tên là `cat_std.broseidon.mc_tutorial.commands`.

```
.
├── pom.xml
└── src
    └── main
        ├── java
        │   └── cat_std
        │       └── broseidon
        │           └── mc_tutorial
        │               ├── commands                    // Đây là package chứa các command của bạn
        │               │   └── HelloCommand.java       // Đây là command của bạn
        │               ├── events                      
        │               │   └── MyEventListener.java    
        │               └── MC_Tutorial.java            
        └── resources
            └── plugin.yml
```

> Sau khi tạo class, bạn cần extends class `CommandExecutor` và override phương thức `onCommand()`. <br>
> Trong phương thức `onCommand()`, bạn có thể kiểm tra label của command, có thể hiểu đây là tên của command của bạn. <br>
> Nếu label của command trùng với tên của command của bạn, bạn có thể thực hiện các hành động mong muốn.

> [!IMPORTANT]
> ### Giải thích về phương thức `onCommand()`
> Các tham số của phương thức `onCommand()`:
> - `CommandSender sender`: Đại diện cho người gửi command. Có thể là người chơi hoặc console.
> - `Command command`: Đại diện cho command được gửi.
> - `String label`: Đại diện cho tên của command được gửi.
> - `String[] args`: Đại diện cho các tham số được gửi kèm theo command.
> 
> Phương thức `onCommand()` trả về một giá trị kiểu `boolean`. <br>
> Nó sẽ trả về `true` nếu command được thực thi thành công, và `false` nếu command không được thực thi thành công.
> 
> Nếu bạn trả về `false` trong phương thức `onCommand()`, Minecraft sẽ tự động gửi một thông báo đến người gửi command: <br>
> `Unknown command. Type "/help" for help.`
> 
> Vì vậy, nếu bạn muốn câu lệnh hoạt động tốt, hãy trả về `true` trong phương thức `onCommand()`.
> 

```java
package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("hello")) {
            sender.sendMessage("Xin chào!"); // Gửi lời chào khi người chơi sử dụng lệnh /hello
        }
        return true;
    }
}
```

Tuy nhiên, đây chưa phải là command hoàn chỉnh. Bạn cần thực hiện các bước tiếp theo để có thể sử dụng command của bạn.

2. **Đăng Ký Command**: Trong class chính của plugin (thường là main class), đăng ký command của bạn bằng cách sử dụng phương thức `getCommand()` và `setExecutor()`.

```java
package cat_std.broseidon.mc_tutorial;

import cat_std.broseidon.mc_tutorial.commands.HelloCommand;
import cat_std.broseidon.mc_tutorial.events.MyEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MC_Tutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        getServer().getPluginManager().registerEvents(new MyEventListener(), this);
        
        getCommand("hello").setExecutor(new HelloCommand()); // Đăng ký lệnh /hello
    }

    @Override
    public void onDisable() {
        // Method này sẽ được gọi khi Plugin được tắt
    }
}

```

Ngoài ra, bạn cần phải khai báo command trong file `resources/plugin.yml` của bạn.

```yaml
commands:
  hello: # Tên command
    description: Hello World Command # Mô tả command
    usage: /<command> # Cách sử dụng command
    aliases: [ hi, helloworld ] # Tên gọi khác của command
```

## Command Với Tham Số

> [!IMPORTANT]
> ### Giải thích về `String[] args`
> 
> Trong lập trình, một mảng (array) là một tập hợp các giá trị được xếp theo thứ tự trong một biến duy nhất. `String[] args` trong Minecraft plugin cũng là một mảng, nhưng nó chứa các tham số (arguments) mà người chơi hoặc console nhập vào phía sau một câu lệnh.
> 
> **Cách Hoạt Động Của Mảng**:
> 
> - Mảng sẽ chứa nhiều giá trị và mỗi giá trị được gán một chỉ số (index) riêng. Chúng ta có thể truy cập các giá trị này bằng cách sử dụng chỉ số.
> - Chỉ số trong mảng bắt đầu từ 0. Điều này có nghĩa là giá trị đầu tiên trong mảng có chỉ số 0, giá trị thứ hai có chỉ số 1, và cứ tiếp tục như vậy.
> 
> **Ví Dụ**:
> 
> Giả sử bạn có lệnh `/mycommand arg1 arg2 arg3`, thì `String[] args` sẽ như sau:
> 
> ```
> args: ["arg1", "arg2", "arg3"]
> index:   0       1       2
> ```
> 
> Để truy cập các giá trị này, bạn sử dụng chỉ số tương ứng. Chẳng hạn, `args[0]` sẽ trả về `"arg1"`, `args[1]` sẽ trả về `"arg2"`, và `args[2]` sẽ trả về `"arg3"`.
> 
> **Ví dụ Sử Dụng**:
> 
> Dưới đây là một ví dụ trong phương thức `onCommand()`:
> ```java
> @Override
> public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
>     if (label.equalsIgnoreCase("mycommand")) {
>         if (args.length > 0) {
>             sender.sendMessage("Các đối số đã nhập:");
>             for (int i = 0; i < args.length; i++) {
>                 sender.sendMessage("- Đối số thứ " + i + ": " + args[i]);
>             }
>         } else {
>             sender.sendMessage("Bạn chưa nhập bất kỳ đối số nào.");
>         }
>         return true;
>     }
>     return false;
> }
> ```
> 
> Trong ví dụ trên, chúng ta sử dụng vòng lặp `for` để duyệt qua tất cả các đối số trong `String[] args` và truy cập chúng bằng chỉ số `i`.
> 
> Hãy nhớ rằng chỉ số trong mảng bắt đầu từ 0 và tăng dần lên.

### Từ bài học trên, bạn có thể tạo một command với tham số như sau:

```java
package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SayHelloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("sayhello")) {
            if (args.length == 0) {
                sender.sendMessage("Vui lòng cung cấp tên của bạn!"); // Gửi thông báo nếu không có tham số
            } else {
                String playerName = args[0]; // Lấy tham số đầu tiên
                sender.sendMessage("Xin chào, " + playerName + " từ plugin của bạn!"); // Gửi tin nhắn với tham số
            }
        }
        return true;
    }
}
```

> Như vậy, bạn đã tạo thành công một command có tham số `/sayhello <name>`. <br>
> Và đừng quên đăng ký command trong class chính của plugin nhé!

## Command Trên Console

Nếu bạn muốn cho phép command được thực thi trên console, bạn cần sử dụng `CommandSender` để kiểm tra ai đã gửi command. <br>
Hãy xem qua ví dụ sau:

```java
package cat_std.broseidon.mc_tutorial.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CheckConsoleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("checkconsole")) {
            if (sender instanceof ConsoleCommandSender) {
                // Người gửi command là console
                sender.sendMessage("Bạn là console.");
            } else if (sender instanceof Player) {
                // Người gửi command là một người chơi
                Player player = (Player) sender;
                sender.sendMessage("Bạn là người chơi với tên: " + player.getName());
            } else {
                // Người gửi không phải là console và cũng không phải là người chơi (ví dụ: command block)
                sender.sendMessage("Bạn là một loại sender khác.");
            }
            return true;
        }
        return false;
    }
}
```

## Kết Luận

> Trong bài hướng dẫn này, bạn đã học cách tạo và quản lý các command trong plugin Minecraft của bạn, bao gồm cả command với tham số và command trên console. Command là một cách mạnh mẽ để tương tác với plugin của bạn và cung cấp tính năng tùy chỉnh cho người chơi và máy chủ.
> 
> Nếu bạn gặp bất kỳ vấn đề nào hoặc có câu hỏi, đừng ngần ngại đặt câu hỏi tại trang [Discussion của dự án](https://github.com/leminhbao308/MC-Tutorial/discussions) để nhận sự trợ giúp từ cộng đồng. 
> Bạn cũng có thể liên hệ với tôi qua [Discord cá nhân](https://discord.com/users/873024375685775361).
> 
> Chúc bạn thành công trong việc phát triển plugin Minecraft của mình!

> [!IMPORTANT]  
> Bài viết này được viết bởi [Lê Minh Bảo]() <br>
> Hãy cho mình một sao nếu bạn thấy bài viết này hữu ích nhé! <br>
> Xin cảm ơn!