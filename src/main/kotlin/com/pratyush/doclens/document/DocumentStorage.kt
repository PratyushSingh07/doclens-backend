package com.pratyush.doclens.document

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID

@Component
class DocumentStorage(
    @Value("\${doclens.storage.path}") basePath: String,
) {
    private val root: Path = Path.of(basePath).also { Files.createDirectories(it) }

    fun store(id: UUID, file: MultipartFile): Path {
        val target = root.resolve("$id.pdf")
        file.inputStream.use { input ->
            Files.copy(input, target)
        }
        return target
    }

    fun delete(id: UUID) {
        Files.deleteIfExists(root.resolve("$id.pdf"))
    }
}
