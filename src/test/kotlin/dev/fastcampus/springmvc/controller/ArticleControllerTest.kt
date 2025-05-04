package dev.fastcampus.springmvc.controller

import dev.fastcampus.springmvc.service.ArticleService
import dev.fastcampus.springmvc.service.ReqCreate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc       //Controller 테스트
@Sql("classpath:db-init/test.sql")
class ArticleControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val articleService: ArticleService
) {

    @Test
    fun get() {
        mockMvc.get("/article/1") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("title") {
                value("title 1")
            }
        }
    }

    @Test
    fun getAll() {
        mockMvc.get("/article/all") {
            contentType = APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.length()") { value(1) }
        }
    }

    @Test
    fun create() {
        mockMvc.post("/article") {
            contentType = APPLICATION_JSON
            content = """
                {
                  "title": "title created",
                  "body": "body created",
                  "authorId": "9999"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title created") }
            jsonPath("body") { value("body created") }
        }

    }

    @Test
    fun update() {
        mockMvc.put("/article/1") {
            contentType = APPLICATION_JSON
            content = """
                {
                  "title": "title updated"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("title") { value("title updated") }
        }

    }

    @Test
    fun delete() {

        val created = articleService.create(ReqCreate(
            title="title",
            body="blabla",
            authorId = 1234
        ))
        val prev = articleService.getAll().size

        mockMvc.delete("/article/${created.id}") {
            contentType = APPLICATION_JSON
            content = """
                {
                  "title": "title created",
                  "body": "body created",
                  "authorId": "9999"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
        }
        val curr = articleService.getAll().size

        assertEquals(prev -1, curr)

    }

}