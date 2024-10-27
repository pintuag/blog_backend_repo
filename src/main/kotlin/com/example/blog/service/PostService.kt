package com.example.blog.service

import com.example.blog.dto.PostDTO
import com.example.blog.dto.toDTO
import com.example.blog.exception.AuthorNotFoundException
import com.example.blog.exception.InvalidRequestException
import com.example.blog.model.Post
import com.example.blog.repository.AuthorRepository
import com.example.blog.repository.PostRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository, private val authorRepository: AuthorRepository
) {

    fun createPost(authorId: Long, title: String, content: String, category: String?): PostDTO {
        val author = authorRepository.findById(authorId).orElseThrow { AuthorNotFoundException("Author not found") }
        val post = Post(title = title, content = content, author = author, category = category)
        return postRepository.save(post).toDTO()
    }

    fun getPostsByAuthor(authorId: Long, page: Int, size: Int): List<PostDTO> {
        val author = authorRepository.findById(authorId).orElseThrow { AuthorNotFoundException("Author not found") }
        val postsPage = postRepository.findByAuthor(author, PageRequest.of(page, size))
        return postsPage.content.map { it.toDTO() }
    }

    // Method to update a post's title and content
    fun updatePost(postId: Long, title: String, content: String): PostDTO {
        val post = postRepository.findById(postId).orElseThrow { InvalidRequestException("Post not found") }
        post.title = title
        post.content = content
        return postRepository.save(post).toDTO()
    }

    // Method to delete a post by ID
    fun deletePost(postId: Long) {
        if (!postRepository.existsById(postId)) {
            throw RuntimeException("Post not found")
        }
        postRepository.deleteById(postId)
    }

    // Method to search for posts by title
    fun searchPostsByTitle(title: String): List<PostDTO> {
        val posts = postRepository.findByTitleContainingIgnoreCase(title)
        return posts.map { it.toDTO() }
    }
}
