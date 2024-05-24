package sample.tdd.unit.chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Outer {

    @BeforeEach
    void outerBefore() {
        System.out.println("outerBefore");
    }

    @Test
    void outerTest() {
        System.out.println("outerTest");
    }

    @AfterEach
    void outerAfter() {
        System.out.println("outerAfter");
    }

    @Nested
    class NestedA {
        @BeforeEach
        void nestedBefore() {
            System.out.println("nestedBefore");
        }

        @Test
        void nestedTestA() {
            System.out.println("nestedTestA");
        }

        @Test
        void nestedTestB() {
            System.out.println("nestedTestB");
        }

        @AfterEach
        void nestedAfter() {
            System.out.println("nestedAfter");
        }
    }

}
