package com.itmo.web_laba_3.dbManager;

import java.io.InputStream;
import java.util.Properties;

public class InfluxDBConfig {

    private static final String PROPERTIES_FILE = "config.properties";
    private static String url;
    private static String token;
    private static String org;
    private static String bucket;

    static {
        try (InputStream input = InfluxDBConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Не удалось найти файл конфигурации: " + PROPERTIES_FILE);
            }
            prop.load(input);

            url = prop.getProperty("influxdb.url");
            token = prop.getProperty("influxdb.token");
            org = prop.getProperty("influxdb.org");
            bucket = prop.getProperty("influxdb.bucket");

            if (url == null || token == null || org == null || bucket == null) {
                throw new RuntimeException("Один или несколько параметров конфигурации отсутствуют в файле.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getToken() {
        return token;
    }

    public static String getOrg() {
        return org;
    }

    public static String getBucket() {
        return bucket;
    }
}