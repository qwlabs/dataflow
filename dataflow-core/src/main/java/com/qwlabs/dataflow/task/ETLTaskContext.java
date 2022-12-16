package com.qwlabs.dataflow.task;

import com.qwlabs.dataflow.Configs;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.guava30.com.google.common.base.Suppliers;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamStatementSet;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import java.util.function.Supplier;

public class ETLTaskContext {
    private final Configs configs;

    private final Supplier<StreamExecutionEnvironment> executionEnv = Suppliers.memoize(this::setupExecutionEnv);
    private final Supplier<StreamTableEnvironment> tableEnv = Suppliers.memoize(this::setupTableEnv);
    private final Supplier<StreamStatementSet> statements = Suppliers.memoize(this::setupStatements);

    public ETLTaskContext(Configs configs) {
        this.configs = configs;
    }

    public StreamStatementSet getStatements() {
        return statements.get();
    }

    public Configs getConfigs() {
        return configs;
    }

    public StreamExecutionEnvironment getExecutionEnv() {
        return executionEnv.get();
    }

    public StreamTableEnvironment getTableEnv() {
        return tableEnv.get();
    }

    private StreamTableEnvironment setupTableEnv() {
        return StreamTableEnvironment.create(getExecutionEnv());
    }

    private StreamExecutionEnvironment setupExecutionEnv() {
        return StreamExecutionEnvironment.getExecutionEnvironment();
    }

    private StreamStatementSet setupStatements() {
        return getTableEnv().createStatementSet();
    }

    public ParameterTool taskConfig(Class<? extends ETLTask> clazz) {
        return getConfigs().load(clazz.getSimpleName());
    }
}
