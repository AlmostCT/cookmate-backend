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
    fun getTopComments(targetId: ObjectId, qty: Int = 1): List<Comment>
    fun getAllComments(targetId: ObjectId): List<Comment>
    fun saveComment(comment: Comment): String
}

@Service("commentService")
class CommentServiceImpl : CommentService {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    override fun getTopComments(targetId: ObjectId, qty: Int): List<Comment> =
            commentRepository.findByTargetOrderByLikesDesc(targetId, qty).sortedBy { it.dislikes - it.likes }.take(qty)

    override fun getAllComments(targetId: ObjectId): List<Comment> =
            commentRepository.findByTarget(targetId)

    override fun saveComment(comment: Comment): String {
        commentRepository.save(comment)
        return "Receipt '${comment.commentId}' saved"
    }
}