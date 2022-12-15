package com.qwlabs.dataflow;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.guava30.com.google.common.base.Suppliers;

import java.io.IOException;
import java.util.function.Supplier;

@Slf4j
public class ConfigFile {
    private static final String DEFAULT_CONFIG_PATH = "application.properties";
    private final String configPath;
    private final Supplier<ParameterTool> ptSupplier = Suppliers.memoize(this::load);


    public ConfigFile(String configPath) {
        this.configPath = configPath;
    }

    public static ConfigFile of(String configPath) {
        return new ConfigFile(configPath);
    }

    public ParameterTool get() {
        return ptSupplier.get();
    }

    private ParameterTool load() {
        if (configPath == null) {
            return getDefault();
        }
        try {
            return ParameterTool.fromPropertiesFile(configPath);
        } catch (IOException e) {
            LOGGER.error("Load config from file '{}' error.", configPath, e);
            throw new RuntimeException(e);
        }
    }

    private ParameterTool getDefault() {
        try {
            return ParameterTool.fromPropertiesFile(ConfigFile.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_PATH));
        } catch (IOException e) {
            LOGGER.error("Load default config file error.", e);
            throw new RuntimeException(e);
        }
    }
}
