package net.hawkelele.quickinfopanel.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.hawkelele.quickinfopanel.config.data.ConfigData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Config {
    public interface UpdateCallback {
        void run(ConfigData configData);
    }

    private final File configFile = FabricLoader.getInstance().getConfigDir().resolve("quick-info-panel.json").toFile();
    private final ConfigData configData;

    public Config() {
        configData = fetchFromFile();
    }

    private ConfigData fetchFromFile() {
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            return new Gson().fromJson(FileUtils.readFileToString(configFile, "utf-8"), ConfigData.class);
        } catch (IOException e) {
            return new ConfigData(); // Returns the default config (emergency fallback)
        }
    }

    public ConfigData update(UpdateCallback updateCallback) throws IOException {
        updateCallback.run(configData);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileUtils.writeStringToFile(configFile, gson.toJson(configData), "utf-8");
        return fetchFromFile();
    }

    public ConfigData get() {
        return configData;
    }
}
