package com.pratyush.doclens.config

import org.slf4j.LoggerFactory
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EmbeddingSmokeTest {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun runEmbeddingSmokeTest(embeddingModel: EmbeddingModel) = CommandLineRunner {
        val vector = embeddingModel.embed("hello world")
        log.info(
            "Embedding smoke test OK -> dimension={}, first 5 values={}",
            vector.size,
            vector.take(5),
        )
    }
}
