package sample.tdd.unit.chap05;

import org.junit.jupiter.api.*;

public class LifecycleTest {
    public LifecycleTest(){
        System.out.println("new LifecycleTest");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("afterAll");
    }

    @BeforeEach
    void setUp(){
        System.out.println("setUp");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @Disabled
    void a() {
        System.out.println("A");
    }

    @Test
    void b() {
        System.out.println("B");
    }
}
