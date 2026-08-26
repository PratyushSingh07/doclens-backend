create table documents (
    id            uuid          primary key,
    filename      varchar(255)  not null,
    content_type  varchar(255)  not null,
    size_bytes    bigint        not null,
    storage_path  varchar(1024) not null,
    uploaded_at   timestamptz   not null default now()
);
