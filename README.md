# MC TUTORIAL 03: CONFIGURATION FILE <br> (Final)

> [!NOTE]
> Trong bài này, bạn sẽ học cách cài đặt và sử dụng tệp cấu hình (configuration file) để tùy chỉnh plugin của bạn.

## Tại sao Cần Tệp Cấu Hình?
Tệp cấu hình là một cách tốt để lưu trữ và quản lý các thiết lập cho plugin của bạn. <br> 
Thay vì chỉ định các thiết lập trong code, bạn có thể cho phép người quản trị máy chủ hoặc người chơi tùy chỉnh plugin của bạn mà không cần sửa đổi mã nguồn.

2. Định Dạng Tệp Cấu Hình
Tệp cấu hình thường được lưu dưới dạng `YAML` hoặc `JSON`. <br> 
Đây là những định dạng dễ đọc và dễ chỉnh sửa. <br>
Trong bài này, chúng ta sẽ sử dụng định dạng `YAML`.

3. Các Thiết Lập Cơ Bản
Trong tệp cấu hình, bạn có thể lưu trữ các thiết lập cơ bản như chuỗi (string), số (number), boolean (true/false), hoặc danh sách (list). <br> 
Ví dụ:

    ```yaml
    welcomeMessage: "Chào mừng bạn đến với server của chúng tôi!"
    enableTeleport: true
    maxPlayers: 100
    ```

4. Đọc Tệp Cấu Hình
Để đọc tệp cấu hình trong code của bạn, bạn cần sử dụng một thư viện hỗ trợ như Bukkit hoặc Spigot đã tích hợp sẵn.

5. Sử Dụng Thiết Lập Trong Plugin
Khi bạn đã đọc tệp cấu hình, bạn có thể sử dụng các giá trị thiết lập để thay đổi hành vi của plugin của bạn. <br> 
Ví dụ:

    ```java
    public void genConfig() {
        String welcomeMessage = getConfig().getString("welcomeMessage");
        boolean enableTeleport = getConfig().getBoolean("enableTeleport");
        int maxPlayers = getConfig().getInt("maxPlayers");
    }
    ```

## Cài Đặt Tệp Cấu Hình
Dưới đây là hướng dẫn cơ bản về cách cài đặt tệp cấu hình cho plugin của bạn:

- **Bước 1: Tạo Tệp Cấu Hình** <br>
Trong thư mục của plugin của bạn, tạo một tệp có định dạng `YAML` (thường là `config.yml`). <br>
Mở tệp cấu hình bằng bất kỳ trình soạn thảo văn bản nào và bắt đầu định nghĩa các thiết lập của bạn.

- **Bước 2: Đọc Tệp Cấu Hình Trong Plugin** <br>
Trong code của plugin của bạn, bạn cần đọc tệp cấu hình để có thể sử dụng các giá trị thiết lập. <br> 
Sử dụng các phương thức theo hướng dẫn sau để làm điều này.

    ```java
    public final class MC_Tutorial extends JavaPlugin {
    
        private FileConfiguration config; // Tệp cấu hình
  
        public void onEnable() {
            // Method này sẽ được gọi khi Plugin được bật
            genConfig(); // Tạo file config.yml
        }
    
        // Đọc cấu hình
        public void genConfig() {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            config = getConfig();
        }
    }
    ```
  
- **Bước 3: Lưu trữ File Config** <br>
Khi bạn build plugin của bạn, đảm bảo bao gồm tệp cấu hình mặc định trong tài nguyên của plugin để người dùng có thể sửa đổi sau này.

    ```
    .
    ├── pom.xml
    └── src
        └── main
            ├── java
            │   └── cat_std
            │       └── broseidon
            │           └── mc_tutorial
            │               ├── commands             
            │               ├── events    
            │               └── MC_Tutorial.java            
            └── resources
                ├── config.yml <--- Tệp cấu hình
                └── plugin.yml
    ```

## Sử Dụng File Config

### Lấy Giá Trị Từ File Config

Tôi có tệp cấu hình như sau:

```yaml
boolean: true
string: "Hello World"
integer: 42
array:
  - 1
  - 2
  - 3

materials:
  - DIAMOND
  - GOLD_INGOT
  - IRON_INGOT

outer:
  inner:
    string: "Hello World from inner"
```

Để lấy giá trị từ tệp cấu hình, bạn có thể tạo class `ConfigManager` như sau:

```java
public class ConfigManager {

    private final FileConfiguration config;

    public ConfigManager(FileConfiguration config) {
        this.config = config;
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    public List<Material> getMaterialList(String path) {
        List<String> stringList = getStringList(path);
        List<Material> materialList = new ArrayList<>();
        for (String string : stringList) {
            materialList.add(Material.valueOf(string));
        }
        return materialList;
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
```

Bạn cũng có thể lấy các giá trị phân cấp như sau:

```java
// Method lấy string trong inner
public String getInnerString(String path) {
    return config.getConfigurationSection("outer").getConfigurationSection("inner").getString(path);
}
```

hoặc

```java
// Method lấy string trong inner
public String getInnerString() {
    return config.getString("outer.inner.string");
}
```

> [!NOTE]
> Cách thứ nhất sẽ trả về `null` nếu `outer` hoặc `inner` không tồn tại. <br>
> Tuy nhiên, nó sẽ linh hoạt hơn khi bạn muốn sử dụng cho những phân cấp có nhiều giá trị.
> 
> Cách thứ hai sẽ trả về `null` nếu `outer.inner.string` không tồn tại. <br>
> Tuy nhiên, nó sẽ nhanh gọn hơn khi bạn muốn sử dụng cho những phân cấp có ít giá trị.

### Sử Dụng Giá Trị Từ File Config

Sau khi bạn đã lấy được giá trị từ tệp cấu hình, bạn có thể sử dụng chúng để thay đổi hành vi của plugin của bạn. <br>
Hãy thay đổi một chút trong class `MC_Tutorial` như sau:

```java
public final class MC_Tutorial extends JavaPlugin {

    private FileConfiguration config; // <-- Hãy bỏ tệp này
    private ConfigManager configManager; // <-- Thay vào đó, hãy tạo một ConfigManager

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        // genConfig();  <-- Hãy bỏ method này
        configManager = new ConfigManager(getConfig()); // <-- Thay vào đó, hãy tạo một ConfigManager
        
        // ...Các code khác
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
```

Giờ, hãy thử tạo một command để sử dụng các giá trị trong tệp cấu hình:

```java
public class CommandTest implements CommandExecutor {

    private final ConfigManager configManager;

    public CommandTest(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(configManager.getString("string"));
            player.sendMessage(String.valueOf(configManager.getBoolean("boolean")));
            player.sendMessage(String.valueOf(configManager.getInt("integer")));
            player.sendMessage(String.valueOf(configManager.getStringList("array")));
            player.sendMessage(String.valueOf(configManager.getMaterialList("materials")));
            player.sendMessage(configManager.getInnerString("string"));
        }
        return true;
    }
}
```

Thêm command này vào class `MC_Tutorial`:

```java
public final class MC_Tutorial extends JavaPlugin {

    private FileConfiguration config;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Method này sẽ được gọi khi Plugin được bật
        genConfig();
        configManager = new ConfigManager(getConfig());
        
        // Đăng ký command
        getCommand("test").setExecutor(new CommandTest(configManager)); // Hãy nhớ truyền ConfigManager vào CommandTest
    }

    @Override
    public void onDisable() {
        // Method này sẽ được gọi khi Plugin được tắt
    }

    public void genConfig() {
        // ...Các code khác
    }
}
```

> [!NOTE]
> Nhớ thêm `test` vào `plugin.yml` để có thể sử dụng command này.
> Sau đó, hãy thử trên server của bạn và xem kết quả nhé!

## Kết Luận

Tệp cấu hình là một cách mạnh mẽ để tùy chỉnh và quản lý plugin Minecraft của bạn. <br> 
Với khả năng đọc và sử dụng các giá trị trong tệp cấu hình, bạn có thể làm cho plugin của mình trở nên linh hoạt và dễ dàng cấu hình theo ý muốn của người quản trị máy chủ và người chơi.

> Trong bài hướng dẫn này, bạn đã học cách cài đặt và sử dụng tệp cấu hình để tùy chỉnh plugin của bạn. <br>
> Bên cạnh đó, bạn đã học được cách sử dụng các phương thức được cung cấp bởi Spigot hoặc Bukkit để đọc tệp cấu hình và sử dụng các giá trị trong plugin của bạn.
> 
> Nếu bạn gặp bất kỳ vấn đề nào hoặc có câu hỏi, đừng ngần ngại đặt câu hỏi tại trang [Discussion của dự án](https://github.com/leminhbao308/MC-Tutorial/discussions) để nhận sự trợ giúp từ cộng đồng. 
> Bạn cũng có thể liên hệ với tôi qua [Discord cá nhân](https://discord.com/users/873024375685775361).
> 
> Chúc bạn thành công trong việc phát triển plugin Minecraft của mình!

> [!IMPORTANT]  
> Bài viết này được viết bởi [Lê Minh Bảo]() <br>
> Hãy cho mình một sao nếu bạn thấy bài viết này hữu ích nhé! <br>
> Xin cảm ơn!
