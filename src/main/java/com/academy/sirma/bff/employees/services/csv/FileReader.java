package com.academy.sirma.bff.employees.services.csv;

import java.util.List;

public abstract class FileReader<T> {
    public abstract List<T> readFromFile();
}
