package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {


    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            write(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            write(dos, r.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> write(dos, ((ListSection) section).getList(), dos::writeUTF);
                    case EDUCATION, EXPERIENCE -> write(dos, ((OrganizationSection) section).getExperienceList(), ors -> {
                        dos.writeUTF(ors.getName());
                        dos.writeUTF(ors.getWebsite());
                        write(dos, ors.getPeriod(), period -> {
                            writeLocalDate(dos, period.getStartDate());
                            writeLocalDate(dos, period.getEndDate());
                            dos.writeUTF(period.getTitle());
                            dos.writeUTF(period.getDescription());
                        });
                    });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readPoint(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readPoint(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private <T> void write(DataOutputStream dos, Collection<T> col, Write<T> write) throws IOException {
        dos.writeInt(col.size());
        for (T t : col) {
            write.writeResume(t);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonthValue());
        dos.writeInt(ld.getDayOfMonth());
    }

    private void readPoint(DataInputStream dis, ReadElement re) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            re.read();
        }
    }

    private Section readSection(DataInputStream dis, SectionType st) throws IOException {
        return switch (st) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EDUCATION, EXPERIENCE -> new OrganizationSection(readList(dis, () -> new Organization(
                    dis.readUTF(),
                    dis.readUTF(),
                    readList(dis, () -> new Period(
                            LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()),
                            LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt()),
                            dis.readUTF(),
                            dis.readUTF()))
            )));
        };
    }

    private <T> List<T> readList(DataInputStream dis, ReadList<T> rl) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(rl.readList());
        }
        return list;
    }

    private interface Write<T> {
        void writeResume(T t) throws IOException;
    }

    private interface ReadElement {
        void read() throws IOException;
    }

    private interface ReadList<T> {
        T readList() throws IOException;
    }
}
