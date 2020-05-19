package com.ggardet.biblio

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [BiblioBeApplication::class])
class BiblioApplicationTests {

    @Test
    fun contextLoads() {
        Assertions.assertTrue(true);
    }

}
