package com.academy.sirma.bff.employees.services.csv.аssignments;

import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.services.csv.CsvFileReader;
import com.academy.sirma.bff.employees.services.helpers.DateParser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.academy.sirma.bff.employees.services.csv.FileConstants.ASSIGNMENTS_CSV_FILE_PATH;

@Component
public class AssignmentsCsvFileReader extends CsvFileReader<Assignment> {

    public AssignmentsCsvFileReader() {
        super(ASSIGNMENTS_CSV_FILE_PATH, AssignmentsCsvFileHeaderUtils.FILE_HEADER_LABELS);
    }

    @Override
    protected Assignment parseEntity(String[] valuesAsString) {

        long employeeId = parseLong(valuesAsString[0]);
        long projectId = parseLong(valuesAsString[1]);
        LocalDate startDate = DateParser.parseLocalDate(valuesAsString[2]);
        LocalDate endDate = DateParser.parseLocalDate(valuesAsString[3]);

        return new Assignment(employeeId, projectId, startDate, endDate);
    }

    private long parseLong(String valuesAsString) {
        try {
            return Long.parseLong(valuesAsString);
        } catch (NumberFormatException nfe) {
            //TODO maybe use some custom exception
            throw new RuntimeException("Invalid Employee ID provided: " + valuesAsString);
        }
    }
}
