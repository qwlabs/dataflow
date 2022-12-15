package com.qwlabs.dataflow.connector;

import java.util.Map;

public class ConnectorContext {
    private final Map<String, String> runtimeOptions;

    public ConnectorContext(Map<String, String> runtimeOptions) {
        this.runtimeOptions = runtimeOptions;
    }

    public String runtimeOption(String key) {
        return runtimeOptions.get(key);
    }
}
