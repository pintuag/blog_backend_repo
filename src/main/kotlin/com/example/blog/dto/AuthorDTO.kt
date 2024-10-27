package com.example.blog.dto

import jakarta.validation.constraints.NotBlank

data class AuthorDTO(
    val id: Long = 0,

    @field:NotBlank(message = "Name is required")
    val name: String
)
