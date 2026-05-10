package net.hawkelele.quickinfopanel.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hawkelele.quickinfopanel.Constants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Config {

    /**
     * ================ PROPERTIES ================
     */

    public Map<String, Boolean> panels = new HashMap<String, Boolean>() {{
        put("coordinates", true);
        put("compass", true);
        put("clock", true);
        put("opposite", true);
        put("biome", true);
        put("weather", true);
    }};

    public String layout = "default";

    public boolean debugBounds = false;

    /**
     * ============= / END PROPERTIES / =============
     */


    private static Config latestConfig;
    private static boolean isDirty = false;
    private static String path = "config/quickinfopanel.json";

    private static void markDirty() {
        isDirty = true;
    }

    private static void markClean() {
        isDirty = false;
    }

    public static Config read() {
        if (latestConfig != null && !isDirty) {
            return latestConfig;
        }

        Config config;
        try {
            Path filepath = Paths.get(path);
            if (!Files.exists(filepath)) {
                Files.createFile(filepath);
            }

            FileReader reader = new FileReader(path);
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            config = gson.fromJson(reader, Config.class);
            if (config == null) {
                config = new Config();
                FileWriter writer = new FileWriter(path);
                gson.toJson(config, writer);
                writer.close();
            }
            reader.close();
        } catch (IOException exception) {
            // Guarantee a config instance with default values is created anyway to reduce friction
            Constants.LOG.error("Failed to read config", exception);
            config = new Config();
        }


        latestConfig = config;
        markClean();

        return config;
    }

    public static void write(Function<Config, Config> change) throws IOException {
        if (latestConfig == null) {
            latestConfig = read();
        }

        save(change.apply(latestConfig));
    }

    public static void save(Config config) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(path);
        gson.toJson(config, writer);
        writer.close();

        markDirty();
    }


}
