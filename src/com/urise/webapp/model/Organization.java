package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final String name;
    private final String website;
    private final List<Period> period;

    public Organization(String name, String website, List<Period> period) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(website);
        Objects.requireNonNull(period);
        this.name = name;
        this.website = website;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!name.equals(that.name)) return false;
        if (!website.equals(that.website)) return false;
        return period.equals(that.period);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + period.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", period=" + period +
                '}';
    }
}
