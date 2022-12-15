package com.qwlabs.dataflow;

import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.table.api.TableConfig;

import java.util.function.Supplier;

public final class TableConfigs {

    public static void setTaskName(TableConfig tableConfig, String name) {
        tableConfig.set(PipelineOptions.NAME, name);
    }

    public static void setTaskName(TableConfig tableConfig, Class<?> taskClass) {
        tableConfig.set(PipelineOptions.NAME, taskClass.getName());
    }

    public static void setTaskName(Supplier<TableConfig> tableConfigSupplier, Class<?> taskClass) {
        setTaskName(tableConfigSupplier.get(), taskClass);
    }
}
