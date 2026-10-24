package com.pratyush.doclens.document

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "document_text")
class DocumentText(
    @Id
    @Column(name = "document_id")
    val documentId: UUID,

    @Column(nullable = false, columnDefinition = "text")
    val content: String,

    @Column(name = "extracted_at", nullable = false)
    val extractedAt: Instant = Instant.now(),
)