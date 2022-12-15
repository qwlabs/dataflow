package com.qwlabs.dataflow;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.Arrays;

@Slf4j
public class Args {
    private static final String CONFIG_KEY = "config";
    private static final String PROFILE_KEY = "profile";
    private final ParameterTool pt;

    public Args(String[] args) {
        LOGGER.info("Args is: {}", Arrays.toString(args));
        this.pt = ParameterTool.fromArgs(args);
    }

    public String configPath() {
        return pt.get(CONFIG_KEY);
    }

    public String profile() {
        return pt.get(PROFILE_KEY);
    }

    public String get(String key) {
        return pt.get(key);
    }

    public static Args of(String[] args) {
        return new Args(args);
    }
}
