package ru.netology.data

import java.sql.Date

data class Comment(
    val noteId: Int,
    val commentId: Int,
    val ownerId: Int,
    val replyTo: Int,
    val message: String,
    val data: Date,
    val remoteOrNot: Boolean
) : Record(){

    override fun toString(): String {
        return "Комментарий \n$noteId \n$commentId \n$ownerId \n$message \n$data"
    }
}
