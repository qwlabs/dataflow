package com.qwlabs.dataflow;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.curator5.org.apache.curator.shaded.com.google.common.base.Splitter;
import org.apache.flink.shaded.guava30.com.google.common.base.Joiner;
import org.apache.flink.shaded.guava30.com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class Configs {
    private static final String TEMPLATE_FORMAT = "%s.config-template";
    private final Args args;
    private final ConfigFile configFile;
    private final Map<String, ParameterTool> ptCache;

    public Configs(Args args) {
        this.args = args;
        this.configFile = ConfigFile.of(args.configPath());
        this.ptCache = Maps.newConcurrentMap();
    }

    public ParameterTool load() {
        return load(null);
    }

    public ParameterTool load(@Nullable String prefix) {
        return load(null, prefix)
            .mergeWith(load(args.profile(), prefix));
    }

    public ParameterTool load(@Nullable String profile, @Nullable String prefix) {
        return ptCache.computeIfAbsent(startPrefix(profile, prefix), this::doLoad);
    }

    private ParameterTool doLoad(String prefix) {
        final var startPrefixLength = prefix.length();
        ParameterTool pt = configFile.get();
        if (startPrefixLength <= 0) {
            return pt;
        }
        Map<String, String> parameters = Maps.newHashMap();
        doLoadTemplate(parameters, pt, prefix);
        pt.toMap().forEach((key, value) -> {
            if (!key.startsWith(prefix)) {
                return;
            }
            parameters.put(key.substring(startPrefixLength + 1), value);
        });
        LOGGER.info("Load config with prefix: {}, value:{}", prefix, parameters);
        return ParameterTool.fromMap(parameters);
    }

    private void doLoadTemplate(Map<String, String> parameters, ParameterTool pt, String prefix) {
        String configTemplate = pt.get(String.format(TEMPLATE_FORMAT, prefix));
        if (configTemplate == null) {
            return;
        }
        Splitter.on(",").split(configTemplate)
            .forEach(template -> {
                parameters.putAll(doLoad(startPrefix(null, template)).toMap());
                parameters.putAll(doLoad(startPrefix(args.profile(), template)).toMap());
            });
    }

    private String startPrefix(@Nullable String profile, @Nullable String prefix) {
        return Joiner.on(".").skipNulls().join(Arrays.asList(profile, prefix));
    }

    protected static Configs of() {
        return of(Args.of(new String[]{}));
    }

    public static Configs of(String[] args) {
        return of(Args.of(args));
    }

    public static Configs of(Args args) {
        return new Configs(args);
    }
}
