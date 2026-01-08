package com.verteregs;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.arc.ClientProxy;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class InterfaceLoaderTest {

    @Inject
    InterfaceLoader loader;

    @Test
    void testLoadExistingClass() throws Exception {
        Interface class1 = loader.loadClass("ConcreteClass1");
        assertNotNull(class1);
        assertEquals("ConcreteClass1", ClientProxy.unwrap(class1)
                .getClass()
                .getSimpleName());

        Interface class2 = loader.loadClass("ConcreteClass2");
        assertNotNull(class2);
        assertEquals("ConcreteClass2", ClientProxy.unwrap(class2)
                .getClass()
                .getSimpleName());
    }

    @Test
    void testLoadNonExistentClass() {
        assertThrows(ClassNotFoundException.class, () -> {
            loader.loadClass("NonExistent");
        });
    }

    @Test
    void testExecuteMethod() throws Exception {
        Interface testClass = loader.loadClass("ConcreteClass1");
        // Just call it to verify runtime execution
        testClass.doSomething();
    }

    @Test
    void testLoadExistingClassV2() throws Exception {
        Interface class1 = loader.loadClass2("ConcreteClass1");
        assertNotNull(class1);
        assertEquals("ConcreteClass1", ClientProxy.unwrap(class1)
                .getClass()
                .getSimpleName());

        Interface class2 = loader.loadClass2("ConcreteClass2");
        assertNotNull(class2);
        assertEquals("ConcreteClass2", ClientProxy.unwrap(class2)
                .getClass()
                .getSimpleName());
    }

    @Test
    void testLoadNonExistentClassV2() {
        assertThrows(ClassNotFoundException.class, () -> {
            loader.loadClass2("NonExistent");
        });
    }

    @Test
    void testExecuteMethodV2() throws Exception {
        Interface testClass = loader.loadClass2("ConcreteClass1");
        // Just call it to verify runtime execution
        testClass.doSomething();
    }
}
