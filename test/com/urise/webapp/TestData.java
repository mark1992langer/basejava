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
        RESUME_1 = new Resume(UUID_1, "Mark");
        RESUME_2 = new Resume(UUID_2, "Igor");
        RESUME_3 = new Resume(UUID_3, "Maksim");
        RESUME_4 = new Resume(UUID_4, "Andrei");

        RESUME_1.setContact(ContactType.TELEPHONE, "+7(921) 855-04-82");
        RESUME_2.setContact(ContactType.TELEPHONE, "+7(977) 342-28-52");
        RESUME_3.setContact(ContactType.TELEPHONE, "+7(921) 565-04-00");
        RESUME_4.setContact(ContactType.TELEPHONE, "+7(926) 681-20-51");

        RESUME_1.setContact(ContactType.SKYPE, "skype 2213");
        RESUME_2.setContact(ContactType.SKYPE, "skype 4410");
        RESUME_3.setContact(ContactType.SKYPE, "skype 6654");
        RESUME_4.setContact(ContactType.SKYPE, "skype 2354");

        RESUME_1.setContact(ContactType.MAIL, "marklanger92@gmail.com");
        RESUME_2.setContact(ContactType.MAIL, "igor45@gmail.com");
        RESUME_3.setContact(ContactType.MAIL, "maksim99@gmail.com");
        RESUME_4.setContact(ContactType.MAIL, "andrei0@gmail.com");

        RESUME_1.setSection(SectionType.OBJECTIVE, new TextSection("Java"));
        RESUME_1.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, инициативность."));
        RESUME_1.setSection(SectionType.ACHIEVEMENT, new ListSection("Mark", "Andrei", "Sasha"));
        RESUME_1.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));

        RESUME_1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("shemodi","https://vk.com/mlanger92",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1, 2019, Month.APRIL,
                                        23,"engineer"," java test"))));
        RESUME_1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Alcatel","https://music.yandex.ru/",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1, 2019, Month.APRIL,
                                        23,"engineer"," java test"))));
    }
}
