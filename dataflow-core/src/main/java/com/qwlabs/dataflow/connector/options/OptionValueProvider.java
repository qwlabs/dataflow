package com.qwlabs.dataflow.connector.options;

import com.qwlabs.dataflow.connector.ConnectorContext;
import org.apache.flink.api.java.utils.ParameterTool;

public interface OptionValueProvider {

    default boolean matches(String key, ParameterTool pt) {
        return false;
    }

    default String provide(String key, ParameterTool pt, ConnectorContext context) {
        return pt.get(key);
    }
}
