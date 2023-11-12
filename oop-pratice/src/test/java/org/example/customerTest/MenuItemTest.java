package org.example.customerTest;

import org.assertj.core.api.Assertions;
import org.example.customer.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuItemTest {

    @DisplayName("메뉴항목을 생성한다.")
    @Test
    void cresteTest() {
        Assertions.assertThatCode(() -> new MenuItem("만두", 5000))
                .doesNotThrowAnyException();
    }
}
