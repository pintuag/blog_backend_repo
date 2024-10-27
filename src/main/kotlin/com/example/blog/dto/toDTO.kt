package com.example.blog.dto

import com.example.blog.model.Author
import com.example.blog.model.Post

fun Author.toDTO(): AuthorDTO {
    return AuthorDTO(id = this.id, name = this.name)
}

fun Post.toDTO(): PostDTO {
    return PostDTO(
        id = this.id,
        title = this.title,
        content = this.content,
        category = this.category
    )
}
