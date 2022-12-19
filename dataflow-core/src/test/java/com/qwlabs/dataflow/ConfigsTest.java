package com.qwlabs.dataflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigsTest {
    @Test
    void should_load_root() {
        var pt = Configs.of().load();
        assertEquals(pt.toMap().size(), 33);
    }

    @Test
    void should_load_by_prefix_source() {
        var pt = Configs.of(new String[]{}).load("comac.airline.source");
        assertEquals(pt.toMap().size(), 13);
        assertEquals(pt.get("connector"), "postgres-cdc");
        assertEquals(pt.get("hostname"), "127.0.0.1");
        assertEquals(pt.get("slot.name"), "__RUNTIME__");
    }

    @Test
    void should_load_by_prefix_sink() {
        var pt = Configs.of(new String[]{}).load("app.airline.sink");
        assertEquals(pt.toMap().size(), 8);
        assertEquals(pt.get("url"), "jdbc:postgresql://localhost:5432/comac_app");
        assertEquals(pt.get("connector"), "jdbc");
    }

    @Test
    void should_load_by_profile() {
        var pt = Configs.of(new String[]{"--profile", "prod"}).load("comac.airline.source");
        assertEquals(pt.toMap().size(), 13);
        assertEquals(pt.get("hostname"), "192.168.3.16");
    }
}
