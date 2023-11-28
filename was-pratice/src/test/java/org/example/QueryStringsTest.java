package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryStringsTest {

    @Test
    void creatTest() {
        QueryStrings queryStrings = new QueryStrings("operand1=11&opertor=*&operand2=33");  // List<QueryString>

        Assertions.assertThat(queryStrings).isNotNull();
    }
}
