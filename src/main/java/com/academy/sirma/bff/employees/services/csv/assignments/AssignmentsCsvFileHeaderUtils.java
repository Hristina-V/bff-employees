package com.academy.sirma.bff.employees.services.csv.аssignments;

import java.util.HashMap;
import java.util.Map;

public class AssignmentsCsvFileHeaderUtils {

    public static final Map<Integer, String> FILE_HEADER_LABELS;

    static {
        FILE_HEADER_LABELS = new HashMap<>();
        FILE_HEADER_LABELS.put(0, "employee_id");
        FILE_HEADER_LABELS.put(1, "project_id");
        FILE_HEADER_LABELS.put(2, "start_date");
        FILE_HEADER_LABELS.put(3, "end_date");
    }
}
