package net.hawkelele.quickinfopanel.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigFile {
    private Config config;
    private String path;

    public ConfigFile(String path) {
        this.path = path;
    }

    public Config read() throws IOException {
        Path filepath = Paths.get(path);
        if (!Files.exists(filepath)) {
            Files.createFile(filepath);
        }

        FileReader reader = new FileReader(path);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Config config = gson.fromJson(reader, Config.class);

        if (config == null) {
            config = new Config();
            FileWriter writer = new FileWriter(path);
            gson.toJson(config, writer);
            writer.close();
        }
        reader.close();

        this.config = config;
        return this.config;
    }

    public void write(Config config) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(path);
        gson.toJson(config, writer);
        writer.close();

        read();
    }
}
