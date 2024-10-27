package com.example.blog.service

import com.example.blog.dto.AuthorDTO
import com.example.blog.dto.toDTO
import com.example.blog.model.Author
import com.example.blog.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun createAuthor(name: String): AuthorDTO {
        val author = Author(name = name)
        return authorRepository.save(author).toDTO()
    }
}
