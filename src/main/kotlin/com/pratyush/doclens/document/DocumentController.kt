package com.pratyush.doclens.document

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("/documents")
class DocumentController(
    private val repository: DocumentRepository,
    private val storage: DocumentStorage,
) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun upload(@RequestParam("file") file: MultipartFile): DocumentResponse {
        if (file.contentType != MediaType.APPLICATION_PDF_VALUE) {
            throw ResponseStatusException(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Only PDF uploads are allowed",
            )
        }
        val id = UUID.randomUUID()
        val path = storage.store(id, file)
        val doc = Document(
            id = id,
            filename = file.originalFilename ?: "unnamed.pdf",
            contentType = file.contentType!!,
            sizeBytes = file.size,
            storagePath = path.toString(),
        )
        return DocumentResponse.from(repository.save(doc))
    }

    @GetMapping
    fun list(): List<DocumentResponse> =
        repository.findAll().map(DocumentResponse::from)
}