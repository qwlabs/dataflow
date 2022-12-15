package com.qwlabs.dataflow.connector.options;

import com.qwlabs.dataflow.connector.ConnectorContext;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.Objects;

public class RuntimeOptionValueProvider implements OptionValueProvider {
    private static final String RUNTIME_FLAG = "__RUNTIME__";
    @Override
    public boolean matches(String key, ParameterTool pt) {
        return Objects.equals(pt.get(key), RUNTIME_FLAG);
    }

    @Override
    public String provide(String key, ParameterTool pt, ConnectorContext context) {
        return context.runtimeOption(key);
    }
}
