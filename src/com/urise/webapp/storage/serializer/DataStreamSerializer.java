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
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(dos, (((ListSection) section).getList()), dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeCollection(dos, (((OrganizationSection) section).getExperienceList()), ors -> {
                        dos.writeUTF(ors.getName());
                        dos.writeUTF(ors.getWebsite());
                        writeCollection(dos, ors.getPeriod(), per -> {
                            writeDate(dos, per.getStartDate());
                            writeDate(dos, per.getEndDate());
                            dos.writeUTF(per.getTitle());
                            dos.writeUTF(per.getDescription());
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
            readPart(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readPart(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, WriteElement<T> writeElement) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writeElement.write(t);
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
        dos.writeInt(ld.getDayOfMonth());
    }

    private void readPart(DataInputStream dis, ReadPart readPart) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            readPart.read();
        }
    }

    private Section readSection(DataInputStream dis, SectionType st) throws IOException {
        return switch (st) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(readList(dis, () -> new Organization(
                    dis.readUTF(),
                    dis.readUTF(),
                    readList(dis, () -> new Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                    ))
            )));
        };
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    private <T> List<T> readList(DataInputStream dis, ReadListElement<T> read) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(read.readList());
        }
        return list;
    }

    private interface WriteElement<T> {
        void write(T t) throws IOException;
    }

    private interface ReadPart {
        void read() throws IOException;
    }

    private interface ReadListElement<T> {
        T readList() throws IOException;
    }
}
