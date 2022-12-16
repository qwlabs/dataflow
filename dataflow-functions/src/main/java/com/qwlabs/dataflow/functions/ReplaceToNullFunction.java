package com.qwlabs.dataflow.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.ScalarFunction;

import java.io.Serializable;
import java.util.Set;

public class ReplaceToNullFunction extends ScalarFunction {
    public static final String NAME = "$replaceToNull";
    private final Options options;

    public ReplaceToNullFunction(Options options) {
        this.options = options;
    }

    public String eval(String value, String... replaceBy) {
        if (value == null) {
            return null;
        }
        value = process(value);
        var replaceBys = Set.of(replaceBy);
        if (replaceBys.contains(value)) {
            return null;
        }
        return value;
    }

    private String process(String value) {
        if (options.trim) {
            value = value.trim();
        }
        return value;
    }


    public static void register(StreamTableEnvironment tableEnv) {
        register(tableEnv, Options.builder().build());
    }

    public static void register(StreamTableEnvironment tableEnv, Options options) {
        tableEnv.createTemporarySystemFunction(options.name, new ReplaceToNullFunction(options));
    }

    @Builder
    @AllArgsConstructor
    public static class Options implements Serializable {
        @Builder.Default
        private final String name = NAME;
        @Builder.Default
        private final boolean trim = true;
    }
}
