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
    private static ConfigData configData = updateFromFile();

    private static ConfigData updateFromFile() {
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            return new Gson().fromJson(FileUtils.readFileToString(configFile, "utf-8"), ConfigData.class);
        } catch (IOException e) {
            return new ConfigData(); // Returns the default config (emergency fallback)
        }
    }

    public static ConfigData update(UpdateCallback updateCallback) throws IOException {
        updateCallback.run(configData);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileUtils.writeStringToFile(configFile, gson.toJson(configData), "utf-8");
        return updateFromFile();
    }

    public static ConfigData get() {
        return configData;
    }
}
