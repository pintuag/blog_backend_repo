package com.example.blog.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostDTO(
    val id: Long = 0,

    @field:NotBlank(message = "Title is required")
    @field:Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    val title: String,

    @field:NotBlank(message = "Content is required")
    @field:Size(min = 10, message = "Content must be at least 10 characters")
    val content: String,

    val category: String? = null
)
