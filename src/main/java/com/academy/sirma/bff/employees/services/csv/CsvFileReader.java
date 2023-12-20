package com.academy.sirma.bff.employees.services.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.academy.sirma.bff.employees.services.csv.FileConstants.CSV_DELIMITER;

public abstract class CsvFileReader<T> extends FileReader {

    protected String fileName;

    private Map<Integer, String> fileHeaderLabels;

    public CsvFileReader(String fileName, Map<Integer, String> fileHeaderLabels) {
        this.fileName = fileName;
        this.fileHeaderLabels = fileHeaderLabels;
    }

    /**
     * Reads list of entities from a CSV file.
     * File is provided through constructor.
     * Before parsing entities header row of CSV file is validated against the expected labels - provided again through the constructor.
     * @return List of Generic entities. Type of entity is provided by the concrete child class.
     */
    public List<T> readFromFile() {
        try (BufferedReader in = new BufferedReader(new java.io.FileReader(fileName))) {
            return readFromFile(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> readFromFile(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            return readFromFile(fileReader);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> readFromFile(BufferedReader in) throws IOException {
        List<T> allItems = new ArrayList<T>();

        //first line is the header line - so we would like to skip it.
        String headerRow = in.readLine();
        if (headerRow == null) {
            return new ArrayList<>();
        }

        String[] headerRowItems = headerRow.split(CSV_DELIMITER);
        validateHeaderRow(headerRowItems);

        String line = in.readLine();

        while (line != null) {
            String[] body = line.split(CSV_DELIMITER);

            allItems.add(parseEntity(body));
            line = in.readLine();
        }

        return allItems;
    }

    protected void validateHeaderRow(String[] headerValues) {
        for (int i = 0; i < headerValues.length; i++) {
            String expectedValue = fileHeaderLabels.get(i);
            String actualValue = headerValues[i];
            if(!expectedValue.equals(actualValue)) {
                throw new RuntimeException(
                    "Invalid column #" + i + 1 + " header row for CSV file: "+ fileName
                        + " . Column name should be: " + expectedValue
                );
            }
        }
    }

    protected abstract T parseEntity(String[] valuesAsString);
}
