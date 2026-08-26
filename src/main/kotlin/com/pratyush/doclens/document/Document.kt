package com.pratyush.doclens.document

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "documents")
class Document(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val filename: String,

    @Column(name = "content_type", nullable = false)
    val contentType: String,

    @Column(name = "size_bytes", nullable = false)
    val sizeBytes: Long,

    @Column(name = "storage_path", nullable = false, length = 1024)
    val storagePath: String,

    @Column(name = "uploaded_at", nullable = false)
    val uploadedAt: Instant = Instant.now(),
)