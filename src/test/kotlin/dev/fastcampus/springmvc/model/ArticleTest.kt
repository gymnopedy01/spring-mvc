package dev.fastcampus.springmvc.model

import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ArticleTest {
    @Test
    fun printArticle() {
        Article(1, "title", "body", authorId = 1234).apply {
            createdAt = LocalDateTime.now()
            updatedAt = LocalDateTime.now()
        }.let { println(">> article: $it") }

    }

}