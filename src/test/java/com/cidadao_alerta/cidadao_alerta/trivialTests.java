package com.cidadao_alerta.cidadao_alerta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class trivialTests {

    @Test
    void assertTrivialPass() {
        Assertions.assertEquals("4", "4");
    }

    @Test
    void assertTrivialFail() {
        Assertions.assertEquals("4", "8");
    }
}
