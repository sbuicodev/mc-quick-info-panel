package net.hawkelele.quickinfopanel.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Config {
    public interface UpdateCallback {
        void run(ConfigData configData);
    }

    private static final File configFile = FabricLoader.getInstance().getConfigDir().resolve("quick-info-panel.json").toFile();
    private static ConfigData configData = new ConfigData();

    private static void updateFromFile() throws IOException {
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        configData = new Gson().fromJson(FileUtils.readFileToString(configFile, "utf-8"), ConfigData.class);
    }

    public static void update(UpdateCallback updateCallback) throws IOException {
        updateCallback.run(configData);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileUtils.writeStringToFile(configFile, gson.toJson(configData), "utf-8");
        updateFromFile();
    }

    public static ConfigData get() {
        return configData;
    }
}
