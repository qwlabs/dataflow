package com.qwlabs.dataflow.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReplaceToNullFunctionTest {
    protected ReplaceToNullFunction defaultFunction = new ReplaceToNullFunction(ReplaceToNullFunction.Options.builder().build());
    protected ReplaceToNullFunction trimFunction = new ReplaceToNullFunction(ReplaceToNullFunction.Options.builder().trim(true).build());
    protected ReplaceToNullFunction unTrimFunction = new ReplaceToNullFunction(ReplaceToNullFunction.Options.builder().trim(false).build());

    @Test
    void should_eval() {

        assertNull(defaultFunction.eval(null));
        assertNull(trimFunction.eval(null));
        assertNull(unTrimFunction.eval(null));

        assertNull(defaultFunction.eval("", ""));
        assertNull(trimFunction.eval("", ""));
        assertNull(unTrimFunction.eval("", ""));

        assertNull(defaultFunction.eval(" ", ""));
        assertNull(trimFunction.eval(" ", ""));
        assertNotNull(unTrimFunction.eval(" ", ""));

        assertNull(defaultFunction.eval("A", "A"));
        assertNull(trimFunction.eval("A", "A"));
        assertNull(unTrimFunction.eval("A", "A"));

        assertNull(defaultFunction.eval(" A ", "A"));
        assertNull(trimFunction.eval(" A ", "A"));
        assertNotNull(unTrimFunction.eval(" A ", "A"));

        assertNull(defaultFunction.eval("A", "A", "B"));
        assertNull(trimFunction.eval("B", "A", "B"));
        assertNotNull(unTrimFunction.eval("A", "B", "C"));
    }
}
