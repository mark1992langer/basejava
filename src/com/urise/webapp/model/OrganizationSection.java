package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class OrganizationSection extends Section {

    private static final long serialVersionUID = 1L;

    private List<Organization> experience;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> experienceList) {
        Objects.requireNonNull(experienceList);
        this.experience = experienceList;
    }

    public List<Organization> getExperienceList() {
        return experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return experience.equals(that.experience);
    }

    @Override
    public int hashCode() {
        return experience.hashCode();
    }

    @Override
    public String toString() {
        return experience.toString();
    }
}
