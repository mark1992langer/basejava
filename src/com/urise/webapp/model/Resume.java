package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private  String uuid;
    private  String fullName;

    private final Map<ContactType, String> contactMap = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sectionMap = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid);
        Objects.requireNonNull(fullName);
        this.uuid = uuid;
        this.fullName = fullName;
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Section getSection(SectionType type) {
        return sectionMap.get(type);
    }

    public String getContact(ContactType type) {
        return contactMap.get(type);
    }

    public void setContact(ContactType type, String value) {
        contactMap.put(type, value);
    }

    public void setSection(SectionType type, Section section) {
        sectionMap.put(type, section);
    }


    @Override
    public String toString() {
        return uuid + " | " + fullName + ". |";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contactMap, resume.contactMap) &&
                Objects.equals(sectionMap, resume.sectionMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contactMap, sectionMap);
    }

    @Override
    public int compareTo(Resume o) {
        int i = fullName.compareTo(o.fullName);
        return i != 0 ? i : uuid.compareTo(o.uuid);
    }
}

