package com.qwlabs.dataflow.connector.options;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CDCSlotNamesTest {
    @Test
    void should_of() {
        assertEquals(CDCSlotNames.of(CDCSlotNamesTest.class), "cdcslotnamestest");
        assertEquals(CDCSlotNames.of(CDCSlotNamesTest.class, (String) null), "cdcslotnamestest_");
        assertEquals(CDCSlotNames.of(CDCSlotNamesTest.class, "1"), "cdcslotnamestest_1");
        assertEquals(CDCSlotNames.of(CDCSlotNamesTest.class, "1", null), "cdcslotnamestest_1_");
        assertEquals(CDCSlotNames.of("a", "1", null), "a_1_");
        assertEquals(CDCSlotNames.of("a"), "a");
    }
}
