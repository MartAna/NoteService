package ru.netology.data

import java.sql.Date

data class Comment(
    val noteId: Int,
    val ownerId: Int,
    val replyTo: Int,
    val message: String,
    val data: Date,
    val remoteOrNot: Boolean
)
