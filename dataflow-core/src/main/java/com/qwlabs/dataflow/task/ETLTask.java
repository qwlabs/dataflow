package com.qwlabs.dataflow.task;

import com.qwlabs.dataflow.Configs;
import com.qwlabs.dataflow.TableConfigs;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;

@SuppressWarnings("checkstyle:VisibilityModifier")
public abstract class ETLTask {
    private static final String CHECKPOINT_INTERVAL = "checkpoint.interval";

    private ETLTaskContext context;

    public ETLTask(String[] args) {
        this.context = new ETLTaskContext(Configs.of(args));
    }

    protected void configTask(ETLTaskContext context) {
        TableConfigs.setTaskName(context.getTableEnv().getConfig(), getTaskName());
        ParameterTool pt = context.taskConfig(this.getClass());
        if (pt.has(CHECKPOINT_INTERVAL)) {
            context.getExecutionEnv().enableCheckpointing(Long.parseLong(pt.get(CHECKPOINT_INTERVAL)), CheckpointingMode.EXACTLY_ONCE);
        }
    }

    protected abstract void define(ETLTaskContext context);

    protected final void bootstrap() {
        configTask(context);
        define(context);
        context.getStatements().execute();
    }

    protected String getTaskName() {
        return getClass().getName();
    }
}
