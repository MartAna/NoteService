package ru.netology.data

import java.sql.Date

data class Comment(
    val noteId: Int,
    val commentId: Int,
    val ownerId: Int,
    val replyTo: Int = 0,
    val message: String = "message",
    val data: Date = Date(2022,8,1),
    val remoteOrNot: Boolean = false
) : Record()
