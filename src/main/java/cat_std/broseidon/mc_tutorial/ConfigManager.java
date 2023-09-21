package cat_std.broseidon.mc_tutorial;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

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

    public String getInnerString() { // Bạn có thể dùng cách này hoặc dùng cách 1 như trong hướng dẫn
        return config.getString("outer.inner.string");
    }

    public FileConfiguration getConfig() {
        return config;
    }
}