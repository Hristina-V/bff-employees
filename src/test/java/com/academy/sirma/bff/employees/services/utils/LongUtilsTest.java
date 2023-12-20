package com.academy.sirma.bff.employees.services.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongUtilsTest {
    private LongUtils longUtils = new LongUtils();

    @Test
    public void it_should_return_first_parameter_when_smaller_for_getSmaller() {
        long a = 234;
        long b = 543;

        long result = longUtils.getSmaller(a,b);

        assertEquals(a, result);
    }

    @Test
    public void it_should_return_second_parameter_when_smaller_for_getSmaller() {
        long a = 234;
        long b = 45;

        long result = longUtils.getSmaller(a,b);

        assertEquals(b, result);
    }

    @Test
    public void it_should_return_correct_value_when_parameters_wre_equal_for_getSmaller() {
        long a = 234;
        long b = 234;

        long result = longUtils.getSmaller(a,b);

        assertEquals(a, result);
    }

    @Test
    public void it_should_return_first_parameter_when_bigger_for_getBigger() {
        long a = 769;
        long b = 543;

        long result = longUtils.getBigger(a,b);

        assertEquals(a, result);
    }

    @Test
    public void it_should_return_second_parameter_when_bigger_for_getBigger() {
        long a = 769;
        long b = 890;

        long result = longUtils.getBigger(a,b);

        assertEquals(b, result);
    }

    @Test
    public void it_should_return_correct_value_when_parameters_are_equal_for_getBigger() {
        long a = 543;
        long b = 543;

        long result = longUtils.getBigger(a,b);

        assertEquals(a, result);
    }

}
