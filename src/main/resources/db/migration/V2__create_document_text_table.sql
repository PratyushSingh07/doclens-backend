create table document_text (
    document_id  uuid         primary key references documents(id) on delete cascade,
    content      text         not null,
    extracted_at timestamptz  not null default now()
);