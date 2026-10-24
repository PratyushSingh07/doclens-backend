package com.pratyush.doclens.document

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class DocumentIngestService(
    private val repository: DocumentRepository,
    private val textRepository: DocumentTextRepository,
    private val storage: DocumentStorage,
    private val textExtractor: PdfTextExtractor,
) {

    @Transactional
    fun ingest(file: MultipartFile): Document {
        val id = UUID.randomUUID()
        val path = storage.store(id, file)
        try {
            val saved = repository.save(
                Document(
                    id = id,
                    filename = file.originalFilename ?: "unnamed.pdf",
                    contentType = file.contentType!!,
                    sizeBytes = file.size,
                    storagePath = path.toString(),
                )
            )
            val text = textExtractor.extract(path)
            textRepository.save(DocumentText(documentId = id, content = text))
            return saved
        } catch (e: Exception) {
            storage.delete(id)
            throw e
        }
    }
}
