@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.service

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Comment
import ru.ifmo.ctddev.cookmate.repo.CommentRepository

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface CommentService {
    fun getComment(id: ObjectId): Comment?
    fun saveComment(comment: Comment): String
}

@Service("receiptService")
class CommentServiceImpl : CommentService {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    override fun getComment(commentId: ObjectId): Comment? = commentRepository.findByCommentId(commentId)

    override fun saveComment(comment: Comment): String {
        commentRepository.save(comment)
        return "Receipt '${comment.commentId}' saved"
    }
}