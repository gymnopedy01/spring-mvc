package dev.fastcampus.springmvc

import dev.fastcampus.springmvc.model.Article
import dev.fastcampus.springmvc.repository.ArticleRepository
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

private val logger = KotlinLogging.logger { }

@SpringBootTest
class SpringMvcApplicationTests(
    @Autowired private val repository: ArticleRepository,
) {

    @Test
    fun contextLoads() {
        val prev = repository.count()
        repository.save(
            Article(
                title = "title",
                body = "body",
                authorId = 1234,
            )
        ).let { logger.debug { it } }
        val curr = repository.count()
        logger.debug { ">> prev: $prev, curr: $curr" }
        assertEquals(prev + 1, curr)


    }

}
