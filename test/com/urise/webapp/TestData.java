package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.setContact(ContactType.MAIL, "marklanger92@gmail.com");
        RESUME_1.setContact(ContactType.TELEPHONE, "+7(921) 855-04-82");

        RESUME_4.setContact(ContactType.TELEPHONE, "+7(926) 681-20-51");
        RESUME_4.setContact(ContactType.SKYPE, "skype:grigory.kislin");

        RESUME_1.setSection(SectionType.OBJECTIVE, new TextSection("Java"));
        RESUME_1.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, инициативность."));
        RESUME_1.setSection(SectionType.ACHIEVEMENT, new ListSection("Mark", "Andrei", "Sasha"));
        RESUME_1.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        RESUME_1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("shemodi","http://www.shemodi.com/",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1, 2019, Month.APRIL,
                                        23,"engineer"," java test"))));

        RESUME_1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Alcatel","http://www.alcatel.ru/",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1, 2019, Month.APRIL,
                                        23,"engineer"," java test"))));

        RESUME_2.setContact(ContactType.TELEPHONE, "+7(977) 275-42-52");
        RESUME_2.setContact(ContactType.SKYPE, "skype");
        RESUME_2.setSection(SectionType.OBJECTIVE, new TextSection("sql"));
    }
}
