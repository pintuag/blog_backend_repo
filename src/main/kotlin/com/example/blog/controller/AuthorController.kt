package com.example.blog.controller

import com.example.blog.dto.AuthorDTO
import com.example.blog.dto.PostDTO
import com.example.blog.exception.AuthorNotFoundException
import com.example.blog.exception.InvalidRequestException
import com.example.blog.service.AuthorService
import com.example.blog.service.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authors")
@Validated
class AuthorController(private val authorService: AuthorService,private val postService: PostService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@Valid @RequestBody request: Map<String, String>): AuthorDTO {
        return authorService.createAuthor(request["name"] ?: throw AuthorNotFoundException("Name is required"))
    }

    @PostMapping("/{authorId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @PathVariable authorId: Long,
        @Valid @RequestBody postRequest: Map<String, String>
    ): PostDTO {
        val title = postRequest["title"] ?: throw InvalidRequestException("Title is required")
        val content = postRequest["content"] ?: throw InvalidRequestException("Content is required")
        val category = postRequest["category"]
        return postService.createPost(authorId, title, content, category)
    }

    @GetMapping("/{authorId}/posts")
    fun getPostsByAuthor(
        @PathVariable authorId: Long,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): List<PostDTO> {
        return postService.getPostsByAuthor(authorId, page, size)
    }

}
