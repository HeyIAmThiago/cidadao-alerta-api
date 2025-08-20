package com.cidadao_alerta.cidadao_alerta;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic("Trivial Tests Suite")
public class TrivialTests {

    @Test
    @Feature("Trivial")
    @Story("Check allure integration")
    void assertTrivialPass() {
        Assertions.assertEquals("4", "4");
    }
}
