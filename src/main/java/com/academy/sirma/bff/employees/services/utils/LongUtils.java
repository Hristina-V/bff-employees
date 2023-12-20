package com.academy.sirma.bff.employees.services.utils;

import org.springframework.stereotype.Component;

@Component
public class LongUtils {

    public long getSmaller(long a, long b) {
        return a < b ? a : b;
    }

    public long getBigger(long a, long b) {
        return a > b ? a : b;
    }
}
