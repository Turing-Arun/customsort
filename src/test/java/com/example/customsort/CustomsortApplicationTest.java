package com.example.customsort;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomsortApplicationTest {

  @Test
  void contextLoads() {}

  // create application instance to call main
  @Test
  void testMain() {
    CustomsortApplication.main(new String[] {});
    assertTrue(true);
  }
}
