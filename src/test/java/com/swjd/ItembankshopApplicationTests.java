package com.swjd;

import com.mysql.jdbc.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItembankshopApplicationTests {

    @Test
    void contextLoads() {
        String a = "";
        if (StringUtils.isNullOrEmpty(a)) {
            System.out.println("a");
        }
    }

}
