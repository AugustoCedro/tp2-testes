package org.example.ex2;

import net.jqwik.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MathFunctionsTest {
    @Provide
    Arbitrary<Integer> evenIntegers() {
        return Arbitraries.integers().map(n -> n * 2);
    }

    @Provide
    Arbitrary<Integer> nonNegativeIntegers() {
        return Arbitraries.integers().greaterOrEqual(0);
    }


    @Property
    void doubleIsGreaterOrEqualForNonNegative(@ForAll("nonNegativeIntegers") int number) {
        long result = MathFunctions.multiplyByTwo(number);
        assertTrue(result >= number,
                () -> "O dobro de " + number + " deveria ser maior ou igual a " + number);
    }

    @Property
    void doubleOfEvenNumberIsEven(@ForAll("evenIntegers") int evenNumber) {
        long result = MathFunctions.multiplyByTwo(evenNumber);
        assertEquals(0, result % 2,
                () -> "O dobro de " + evenNumber + " deveria ser par");
    }




}
