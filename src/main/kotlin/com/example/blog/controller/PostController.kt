package com.example.blog.controller

import com.example.blog.dto.PostDTO
import com.example.blog.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody updateRequest: Map<String, String>): PostDTO {
        return postService.updatePost(postId, updateRequest["title"]!!, updateRequest["content"]!!)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable postId: Long) {
        postService.deletePost(postId)
    }

    @GetMapping("/search")
    fun searchPosts(@RequestParam title: String): List<PostDTO> {
        return postService.searchPostsByTitle(title)
    }
}
