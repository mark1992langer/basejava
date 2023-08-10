package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.Objects;

public class ResumeTestData {

    ResumeTestData(String uuid, String fullName){
        Objects.requireNonNull(uuid);
        Objects.requireNonNull(fullName);
    }

    public static Resume resumeGenerate (String uuid, String fullName){
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.TELEPHONE,"+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE,"skype:grigory.kislin");
        resume.setContact(ContactType.MAIL, "marklanger92@gmail.com");
        resume.setContact(ContactType.LINKEDIN,"https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB,"https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW,"https://stackoverflow.com/users/548473");
        resume.setContact(ContactType.HOMEPAGE,"http://gkislin.ru/");
        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, инициативность."));
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок обучения по Java."));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection("Организация команды","Разработка Web " +
                "приложения","Реализация я Rich Internet Application","Сбор статистики сервисов"));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection("Java","Scala","DB","JavaScript"));
        resume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Alcatel","http://www.alcatel.ru/",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1, 2019, Month.APRIL,
                                        23,"engineer"," java test"))));
        resume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Alcatel","http://www.alcatel.ru/",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1, 2019, Month.APRIL,
                                        23,"engineer"," java test"))));
        resume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Alcatel","http://www.alcatel.ru/",
                                new Period(2000, Month.JANUARY, 10, 2010,Month.APRIL,
                                        18, "Java junior", "java test"),
                                new Period(2010, Month.AUGUST, 1,"engineer"," java test"))));
        return resume;
    }
}
