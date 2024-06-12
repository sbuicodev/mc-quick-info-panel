package net.hawkelele.quickinfopanel.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.hawkelele.quickinfopanel.config.settings.GeneralSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Config {
    public interface Update {
        void run(GeneralSettings settings);

    }

    private static Config instance;

    private GeneralSettings settings;

    private Config() {
        load();
    }

    private void load() {
        try {
            String json = FileUtils.readFileToString(getFile(), "UTF-8");
            settings = new Gson().fromJson(json, GeneralSettings.class);
        } catch (IOException e) {
            settings = defaults();
            write(settings);
        }
    }

    public void write(GeneralSettings settings) {
        this.settings = settings;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(settings);
        try {
            FileUtils.writeStringToFile(getFile(), json, "UTF-8");
        } catch (IOException e) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.literal(e.toString()).formatted(Formatting.RED));
        }
    }

    private File getFile() {
        return FileUtils.getFile(FabricLoader.getInstance().getConfigDir().toString(), "quick-info-panel.json");
    }

    private GeneralSettings defaults() {
        GeneralSettings data = new GeneralSettings();
        data.position.applyPreset("default");
        return data;
    }

    public static Config getInstance() {
        return instance == null ? instance = new Config() : instance;
    }

    public GeneralSettings settings() {
        return settings;
    }

    public void update(Update update) {
        update.run(settings);
        write(settings);
    }
}
