package net.hawkelele.quickinfopanel.platform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.platform.services.IConfigHelper;
import net.hawkelele.quickinfopanel.platform.services.IConfigStore;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FabricConfigHelper implements IConfigHelper, IConfigStore {
    private static final String PATH = "config/quickinfopanel.json";
    private static Config latestConfig;

    @Override
    public void registerConfigScreen() {
        // Fabric exposes the config UI through the Mod Menu entrypoint in
        // net.hawkelele.quickinfopanel.config.ModMenuIntegration.
    }

    @Override
    public Config read() {
        if (latestConfig != null) {
            return latestConfig;
        }

        Config config;
        try {
            Path filepath = Paths.get(PATH);
            if (!Files.exists(filepath)) {
                Files.createFile(filepath);
            }

            try (FileReader reader = new FileReader(PATH)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                config = gson.fromJson(reader, Config.class);
                if (config == null) {
                    config = new Config();
                    try (FileWriter writer = new FileWriter(PATH)) {
                        gson.toJson(config, writer);
                    }
                }
            }
        } catch (IOException exception) {
            Constants.LOG.error("Failed to read config", exception);
            config = new Config();
        }

        latestConfig = config;
        return config;
    }

    @Override
    public void save(Config config) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(PATH)) {
            gson.toJson(config, writer);
        }

        latestConfig = config;
    }
}
