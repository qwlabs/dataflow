package com.qwlabs.dataflow.functions;


import org.apache.flink.table.functions.TableFunction;

public class SplitFunction extends TableFunction<String> {
    public static final String DEFAULT_NAME = "$split";

    public void eval(String value, String regex) {
        if (value == null) {
            return;
        }
        for (String s : value.split(regex)) {
            collect(s);
        }
    }
}
