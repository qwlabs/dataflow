package com.qwlabs.dataflow.connector.options;

import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.shaded.guava30.com.google.common.base.Joiner;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public final class CDCSlotNames {
    private CDCSlotNames() {
    }

    public static String of(@Nonnull Class<?> clazz, String... suffixs) {
        return of(StringUtils.lowerCase(clazz.getSimpleName()), suffixs);
    }

    public static String of(@Nonnull String prefix, String... suffixs) {
        List<String> values = Lists.newArrayList();
        values.add(prefix);
        values.addAll(Arrays.asList(suffixs));
        return Joiner.on("_")
            .useForNull("")
            .join(values);
    }
}
