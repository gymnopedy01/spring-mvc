package dev.fastcampus.springmvc.service

import dev.fastcampus.springmvc.exception.NoArticleFound
import dev.fastcampus.springmvc.model.Article
import dev.fastcampus.springmvc.repository.ArticleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val repository: ArticleRepository
) {

    fun get(id: Long?): Article {
        return repository.findByIdOrNull(id) ?: throw NoArticleFound("No article id: $id")
    }

//    fun getAll(): List<Article> {
//        return repository.findAll()
//    }

    fun getAll(title: String? = null): List<Article> {
        return if (title.isNullOrEmpty()) {
            repository.findAll()
        } else {
            repository.findAllByTitleContains(title)
        }
    }

    @Transactional
    fun create(request: ReqCreate): Article {
        return repository.save(
            Article(
                title = request.title,
                body = request.body,
                authorId = request.authorId
            )
        )
    }


    @Transactional
    fun update(id: Long, request: ReqUpdate) :Article {
        return repository.findByIdOrNull(id)?.let { article ->
            request.title?.let { article.title = it }
            request.body?.let { article.body = it }
            request.authorId?.let { article.authorId = it }
            //article
            repository.save(article)
        } ?: throw NoArticleFound("No article id: $id")
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}

data class ReqCreate(
    val title: String,
    val body: String?,
    val authorId: Long?
)

data class ReqUpdate(
    val title: String? = null,
    val body: String? = null,
    val authorId: Long? = null
)