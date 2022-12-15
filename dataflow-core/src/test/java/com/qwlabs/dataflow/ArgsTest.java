package com.qwlabs.dataflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArgsTest {

    @Test
    void should_configPath() {
        assertNull(Args.of(new String[]{}).configPath());
        assertEquals(Args.of(new String[]{"--config", "application.properties"}).configPath(), "application.properties");
    }

    @Test
    void should_profile() {
        assertNull(Args.of(new String[]{}).profile());
        assertEquals(Args.of(new String[]{"--profile", "prod"}).profile(), "prod");
    }
}
