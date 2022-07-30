package ru.netology.data

import java.sql.Date

data class Note(
    val noteId: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val privacy: Int,
    val commentPrivacy: Int,
    val data: Date,
    val remoteOrNot: Boolean
)
