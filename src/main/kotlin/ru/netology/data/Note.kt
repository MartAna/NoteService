package ru.netology.data

import java.sql.Date

data class Note (
    val noteId: Int,
    val ownerId: Int,
    val title: String = "title",
    val text: String = "text",
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
    val data: Date = Date(2022,8,1)
) : Record()

