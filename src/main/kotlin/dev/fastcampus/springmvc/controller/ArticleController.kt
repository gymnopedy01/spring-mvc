package dev.fastcampus.springmvc.controller

import dev.fastcampus.springmvc.model.Article
import dev.fastcampus.springmvc.service.ArticleService
import dev.fastcampus.springmvc.service.ReqCreate
import dev.fastcampus.springmvc.service.ReqUpdate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/article")
class ArticleController(
    private val service: ArticleService
) {

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): Article {
        return service.get(id)
    }

    @GetMapping("/all")
    fun getAll(@RequestParam title: String?): List<Article> {
        return service.getAll(title)
    }

    @PostMapping("")
    fun create(@RequestBody request: ReqCreate): Article {
        return service.create(request)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ReqUpdate): Article {
        return service.update(id, request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return service.delete(id)
    }
}