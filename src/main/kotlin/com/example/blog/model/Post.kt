package com.example.blog.model

import jakarta.persistence.*

@Entity
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var title: String,
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "author_id")
    val author: Author,
    val category: String? = null
)
