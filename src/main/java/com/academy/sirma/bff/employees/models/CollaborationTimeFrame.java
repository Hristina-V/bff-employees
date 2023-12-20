package com.academy.sirma.bff.employees.models;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class  CollaborationTimeFrame {
    private LocalDate startDate;

    private LocalDate endDate;

    public CollaborationTimeFrame(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getDays() {
        long daysBetween = DAYS.between(startDate, endDate);

        return daysBetween;
    }
}
