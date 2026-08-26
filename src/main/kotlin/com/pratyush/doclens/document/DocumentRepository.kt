package com.pratyush.doclens.document

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DocumentRepository: JpaRepository<Document, UUID>