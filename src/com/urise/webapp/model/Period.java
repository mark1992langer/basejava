package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class Period implements Serializable {
    private static final long serialVersionUID = 1L;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Period(int startYear, Month startMonth, int startDay, String title, String description) {
        this(of(startYear, startMonth, startDay), now(), title, description);
    }

    public Period(int startYear, Month startMonth, int startDay, int endYear,
                  Month endMonth, int endDay, String title, String description) {
        this(of(startYear, startMonth, startDay), of(endYear, endMonth, endDay), title, description);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (!startDate.equals(period.startDate)) return false;
        if (!endDate.equals(period.endDate)) return false;
        if (!title.equals(period.title)) return false;
        return Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Period{" + startDate + " - " + endDate +
                " ( " + title  + " ) " + description + '}';
    }
}
