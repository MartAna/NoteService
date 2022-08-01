import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import ru.netology.data.Comment
import ru.netology.data.Note
import ru.netology.data.Record
import ru.netology.service.CommentNotDeletedException
import ru.netology.service.CommentNotFoundException
import ru.netology.service.NoteNotFoundException
import ru.netology.service.NoteService


class NoteServiceTest {

    @Test
    fun addNote() {

        val service = NoteService()

        val note = service.add(Note(noteId = 0, ownerId = 1))

        assertTrue(note != 0)

    }

    @Test
    fun addComment() {

        val service = NoteService()

        service.add(Note(0, 1))
        val comment = service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertTrue(comment != 0)

    }

    @Test(expected = NoteNotFoundException::class)
    fun addCommentError() {

        val service = NoteService()

        service.add(Note(0, 1))
        val comment = service.add(Comment(noteId = 2, ownerId = 1, commentId = 0))

        assertTrue(comment != 0)

    }

    @Test(expected = IllegalArgumentException::class)
    fun addFalse() {

        val service = NoteService()

        val note = service.add(Record())

        assertTrue(note != 0)

    }

    @Test
    fun deleteNoteTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))

        assertTrue(service.delete("note", 1))

    }

    @Test
    fun deleteNoteFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))

        assertFalse(service.delete("note", 2))

    }

    @Test
    fun deleteNoteWithCommentTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertTrue(service.delete("note", 1))

    }


    @Test
    fun deleteCommentTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertTrue(service.delete("comment", 1))

    }


    @Test
    fun deleteCommentFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertFalse(service.delete("comment", 2))

    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentError() {

        val service = NoteService()

        service.add(Note(0, 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0, remoteOrNot = true))

        assertFalse(service.delete("comment", 1))

    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteError() {

        val service = NoteService()

        service.add(Note(0, 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertTrue(service.delete("string", 2))

    }

    @Test
    fun editNoteTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))


        assertTrue(service.edit(Note(noteId = 0, ownerId = 2, title = "New title"), 1))

    }

    @Test
    fun editNoteFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))

        assertFalse(service.edit(Note(noteId = 0, ownerId = 2, title = "New title"), 2))

    }

    @Test
    fun editCommentTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertTrue(service.edit(Comment(noteId = 1, commentId = 1, ownerId = 2, message = "New message"), 1))

    }

    @Test
    fun editCommentFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertFalse(service.edit(Comment(noteId = 1, commentId = 1, ownerId = 2, message = "New message"), 2))

    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentError() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0, remoteOrNot = true))

        assertFalse(service.edit(Comment(noteId = 1, commentId = 1, ownerId = 2, message = "New message"), 1))

    }

    @Test(expected = IllegalArgumentException::class)
    fun editError() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertTrue(service.edit(Record(), 1))

    }

    @Test
    fun getNoteTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 2))

        val result = service.get(1)

        assertTrue(result != null)

    }


    @Test
    fun getNoteFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 2))

        val result = service.get(3)

        assertTrue(result.isNullOrEmpty())

    }


    @Test
    fun getByIdTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        val note = service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 2))

        val result = service.getById(2)

        assertTrue(note == result?.noteId)

    }

    @Test
    fun getByIdFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 2))

        val result = service.getById(5)

        assertTrue(result == null)

    }

    @Test
    fun getCommentTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(1, 0, 1))
        service.add(Comment(2, 0, 1))
        service.add(Comment(1, 0, 1))

        val result = service.getComment(1)

        assertTrue(result != null)

    }


    @Test
    fun getCommentFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(1, 0, 1))
        service.add(Comment(2, 0, 1))
        service.add(Comment(1, 0, 1))

        val result = service.getComment(3)

        assertTrue(result.isNullOrEmpty())

    }

    @Test
    fun restoreCommentTrue() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0, remoteOrNot = true))

        assertTrue(service.restoreComment(1))

    }

    @Test(expected = CommentNotDeletedException::class)
    fun restoreCommentError() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0, remoteOrNot = false))

        assertTrue(service.restoreComment(1))

    }


    @Test
    fun restoreCommentFalse() {

        val service = NoteService()

        service.add(Note(noteId = 0, ownerId = 1))
        service.add(Comment(noteId = 1, ownerId = 1, commentId = 0))

        assertFalse(service.restoreComment(2))

    }


}