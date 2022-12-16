package com.qwlabs.dataflow.task;

import com.qwlabs.dataflow.Configs;
import com.qwlabs.dataflow.TableConfigs;

@SuppressWarnings("checkstyle:VisibilityModifier")
public abstract class ETLTask {

    protected ETLTaskContext context;

    public ETLTask(String[] args) {
        this.context = new ETLTaskContext(Configs.of(args));
    }

    protected abstract void define(ETLTaskContext context);

    protected final void bootstrap() {
        TableConfigs.setTaskName(context.getTableEnv().getConfig(), getTaskName());
        define(context);
        context.getStatements().execute();
    }

    protected String getTaskName() {
        return getClass().getName();
    }
}
