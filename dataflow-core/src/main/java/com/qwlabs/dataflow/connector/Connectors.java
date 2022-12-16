package com.qwlabs.dataflow.connector;

import com.qwlabs.dataflow.Configs;
import com.qwlabs.dataflow.connector.options.ConnectorAllowOptions;
import com.qwlabs.dataflow.connector.options.ConnectorOptionValues;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.table.api.TableDescriptor;

import java.util.Optional;
import java.util.Set;

@Slf4j
public final class Connectors {

    private static final String SOURCE_PREFIX_TEMPLATE = "%s.source";
    private static final String SINK_PREFIX_TEMPLATE = "%s.sink";
    private static final String CONNECTOR_KEY = "connector";
    private static final String DEFAULT_CONNECTOR = KnownConnectors.JDBC;

    private Connectors() {
    }

    public static TableDescriptor.Builder describe(Configs configs, String prefix, ConnectorContext context) {
        return describe(configs, prefix, context, null);
    }

    public static TableDescriptor.Builder describe(Configs configs, String prefix, ConnectorContext context, Set<String> allowOptions) {
        var pt = configs.load(prefix);
        String connector = getConnector(configs, prefix);
        TableDescriptor.Builder builder = TableDescriptor.forConnector(connector);
        final var guessAllowOptions = Optional.ofNullable(allowOptions)
            .orElseGet(() -> ConnectorAllowOptions.get(connector));
        pt.toMap().forEach((key, value) -> {
            if (allow(guessAllowOptions, key)) {
                builder.option(key, ConnectorOptionValues.provide(key, value, pt, context));
            }
        });
        return builder;
    }

    public static TableDescriptor log(TableDescriptor descriptor) {
        return log("", descriptor);
    }

    public static TableDescriptor log(String tableName, TableDescriptor descriptor) {
        Optional.ofNullable(descriptor)
            .ifPresent(td -> LOGGER.info("{}:\n{};", tableName, td));
        return descriptor;
    }

    private static boolean allow(Set<String> allowOptions, String key) {
        if (allowOptions.contains(key)) {
            return true;
        }
        return allowOptions.stream().anyMatch(key::startsWith);
    }

    public static String getConnector(Configs configs, String prefix) {
        return getConnector(configs.load(prefix));
    }

    public static String getConnector(ParameterTool pt) {
        return pt.get(CONNECTOR_KEY, DEFAULT_CONNECTOR);
    }

    public static String sourcePrefix(String prefix) {
        return String.format(SOURCE_PREFIX_TEMPLATE, prefix);
    }

    public static String sinkPrefix(String prefix) {
        return String.format(SINK_PREFIX_TEMPLATE, prefix);
    }
}
