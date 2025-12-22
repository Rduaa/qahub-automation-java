package core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new IllegalStateException("config.properties not found in src/test/resources");
            }
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {}

    public static String get(String key) {
        // system property overrides file value (for CI)
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys.trim();

        String val = PROPS.getProperty(key);
        if (val == null || val.isBlank()) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return val.trim();
    }
}