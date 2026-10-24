package com.pratyush.doclens.document

import java.time.Instant
import java.util.UUID

data class DocumentTextResponse(
    val documentId: UUID,
    val content: String,
    val extractedAt: Instant,
) {
    companion object {
        fun from(text: DocumentText) = DocumentTextResponse(
            documentId = text.documentId,
            content = text.content,
            extractedAt = text.extractedAt,
        )
    }
}