package com.pratyush.doclens.document

import java.time.Instant
import java.util.UUID

data class DocumentResponse(
    val id: UUID,
    val filename: String,
    val contentType: String,
    val sizeBytes: Long,
    val uploadedAt: Instant,
) {
    companion object {
        fun from(doc: Document) = DocumentResponse(
            id = doc.id,
            filename = doc.filename,
            contentType = doc.contentType,
            sizeBytes = doc.sizeBytes,
            uploadedAt = doc.uploadedAt,
        )
    }
}