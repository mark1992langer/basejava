package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final Organization EMPTY = new Organization("","", Period.EMPTY);

    private String name;
    private String website;
    private List<Period> period;

    public Organization() {
    }

    public Organization(String name, String website, Period... args) {
        this(name, website, Arrays.asList(args));
    }

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
        return Objects.equals(name, that.name) && Objects.equals(website, that.website) && Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, website, period);
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
