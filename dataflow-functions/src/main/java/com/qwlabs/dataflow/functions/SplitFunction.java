package com.qwlabs.dataflow.functions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.TableFunction;

import java.io.Serializable;

public class SplitFunction extends TableFunction<String> {
    public static final String NAME = "$split";
    private final Options options;

    public SplitFunction(Options options) {
        this.options = options;
    }

    public void eval(String value, String regex) {
        if (value == null) {
            return;
        }
        for (String s : value.split(regex)) {
            collect(s);
        }
    }

    public static void register(StreamTableEnvironment tableEnv) {
        register(tableEnv, Options.builder().build());
    }

    public static void register(StreamTableEnvironment tableEnv, Options options) {
        tableEnv.createTemporarySystemFunction(options.name, new SplitFunction(options));
    }

    @AllArgsConstructor
    @Builder
    public static class Options implements Serializable {
        @Builder.Default
        private final String name = NAME;
    }

}
