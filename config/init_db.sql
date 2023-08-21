CREATE TABLE resume
(
    uuid CHAR (36) PRIMARY KEY NOT NULL,
    full_name TEXT NOT NULL
);

CREATE TABLE contact
(
    id SERIAL,
    resume_uuid CHAR (36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
    type TEXT NOT NULL,
    value TEXT NOT NULL
);

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);
