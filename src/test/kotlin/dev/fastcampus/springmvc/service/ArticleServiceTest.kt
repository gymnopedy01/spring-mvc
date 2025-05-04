package dev.fastcampus.springmvc.service

import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback

@SpringBootTest
@ExtendWith(MockitoExtension::class)
@Transactional
@Rollback
class ArticleServiceTest(
    @Autowired private val service: ArticleService,
//    @Mock private val service: ArticleService
) {
    @Test
    fun `create and get`() {
//        Mockito.`when`(service.get(1)).thenReturn(
//            Article(1, "title", "blabla", 1234)
//        )

        val article = service.create(ReqCreate(
            title="title",
            body="blabla",
            authorId = 1234
        )).let { service.get(it.id)}


        assertEquals("title", article.title)
        assertEquals("blabla", article.body)
        assertEquals(1234, article.authorId)
    }

    @Test
    fun getAll() {

        repeat(5) {i ->
            service.create(ReqCreate(
                title="title $i ",
                body="blabla $i",
                authorId = 1234
            ))
        }

//        assertEquals(5, service.getAll().size)
        assertTrue(service.getAll().size >= 5)

    }

    @Test
    fun testGetAll() {
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {

        val created = service.create(ReqCreate(
            title="title",
            body="blabla",
            authorId = 1234
        ))

        service.update(created.id!!, ReqUpdate(title="updated"))

        val updated = service.get(created.id)

        assertEquals("updated", updated.title)

    }

    @Test
    fun delete() {

        val created = service.create(ReqCreate(
            title="title",
            body="blabla",
            authorId = 1234
        ))

        val prev = service.getAll().size
        service.delete(created.id!!)
        val curr = service.getAll().size

        assertEquals(prev - 1, curr)

    }

}