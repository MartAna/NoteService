package ru.netology.service

import ru.netology.data.Comment
import ru.netology.data.Note
import ru.netology.data.Record
import java.lang.IllegalArgumentException

class NoteService {

    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()

    private var countNoteId = 0
    private var countCommentId = 0

    fun add(record: Record): Int {
        when (record) {
            is Note -> {
                val newNote = record.copy(noteId = countNoteId + 1)
                notes.add(newNote)
                countNoteId++
                return notes.last().noteId
            }
            is Comment -> {
                for (item in notes) {
                    if (record.noteId == item.noteId) {
                        val newComment = record.copy(commentId = countCommentId + 1)
                        comments.add(newComment)
                        countCommentId++
                        return comments.last().commentId
                    }
                }
                throw NoteNotFoundException()
            }
            else ->
                throw IllegalArgumentException("Unknown argument")
        }
    }

    fun delete(record: String, id: Int): Boolean {
        when (record) {
            "note" -> {
                for (item in notes) {
                    if (id == item.noteId) {
                        val number = notes.indexOf(item)
                        notes[number] = item.copy(remoteOrNot = true)
                        for (it in comments) {
                            if (id == it.noteId) {
                                val numberCom = comments.indexOf(it)
                                comments[numberCom] = it.copy(remoteOrNot = true)
                                return true
                            }
                        }
                        return true
                    }
                    return false
                }
            }
            "comment" -> {
                for (item in comments) {
                    if (id == item.commentId) {
                        if (!item.remoteOrNot) {
                            val number = comments.indexOf(item)
                            comments[number] = item.copy(remoteOrNot = true)
                            return true
                        }
                        throw CommentNotFoundException()
                    }

                }
                return false
            }
        else ->
        throw IllegalArgumentException("Unknown argument")
    }
    return false
}

fun edit(record: Record, id: Int): Boolean {
    when (record) {
        is Note -> {
            for (item in notes) {
                if (id == item.noteId) {
                    val number = notes.indexOf(item)
                    notes[number] = record.copy(noteId = item.noteId, ownerId = item.ownerId, data = item.data)
                    return true
                }
                return false
            }
        }
        is Comment -> {
            for (item in comments) {
                if (id == item.commentId) {
                    if (!item.remoteOrNot) {
                        val number = comments.indexOf(item)
                        comments[number] = record.copy(
                            commentId = item.commentId,
                            noteId = item.noteId,
                            ownerId = item.ownerId,
                            data = item.data
                        )
                        return true
                    }
                    throw CommentNotFoundException()
                }
                return false
            }
        }
        else ->
            throw IllegalArgumentException("Unknown argument")
    }
    return false
}

fun get(ownerId: Int): List<Note>? {
    for (item in notes) {
        if (ownerId == item.ownerId) {
            val list = mutableListOf<Note>()
            if (!item.remoteOrNot) list.add(item)
            return list

        }
    }
    return null

}

fun getById(noteId: Int): Note? {
    for (item in notes) {
        if (noteId == item.noteId) {
            return item
        }
    }
    return null
}

fun getComment(noteId: Int): List<Comment>? {
    for (item in comments) {
        if (noteId == item.noteId) {
            val list = mutableListOf<Comment>()
            if (!item.remoteOrNot) list.add(item)
            return list
        }
    }
    return null
}

fun restoreComment(id: Int): Boolean {
    for (item in comments) {
        if (id == item.commentId) {
            if(item.remoteOrNot) {
                val number = comments.indexOf(item)
                comments[number] = item.copy(remoteOrNot = false)
                return true
            }
            throw CommentNotDeletedException()
        }
    }
    return false
}

}


