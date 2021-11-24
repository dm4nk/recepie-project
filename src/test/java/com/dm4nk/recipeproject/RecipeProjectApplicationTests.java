package com.dm4nk.recipeproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void failTest(){
        assertEquals(1, 2);
    }

}
