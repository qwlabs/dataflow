package com.qwlabs.dataflow.connector.options;

import com.qwlabs.dataflow.connector.ConnectorContext;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.List;

public final class ConnectorOptionValues {
    private static final List<OptionValueProvider> PROVIDERS = initProcessors();
    private static final OptionValueProvider DEFAULT_PROVIDER = new OptionValueProvider() {
    };

    private ConnectorOptionValues() {
    }

    private static List<OptionValueProvider> initProcessors() {
        return List.of(new RuntimeOptionValueProvider());
    }

    //TODO use register
    public static String provide(String key, String value, ParameterTool pt, ConnectorContext context) {
        if (value == null) {
            return null;
        }
        return PROVIDERS.stream().filter(processor -> processor.matches(key, pt))
            .findFirst()
            .orElse(DEFAULT_PROVIDER)
            .provide(key, pt, context);
    }
}
