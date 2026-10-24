package com.pratyush.doclens.document

import org.apache.pdfbox.Loader
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class PdfTextExtractor {

    fun extract(path: Path): String =
        Loader.loadPDF(path.toFile()).use { pdf ->
            PDFTextStripper().getText(pdf)
        }
}