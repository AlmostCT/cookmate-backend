package ru.ifmo.ctddev.cookmate.repo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import ru.ifmo.ctddev.cookmate.model.Comment

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface CommentRepository : MongoRepository<Comment, ObjectId> {
    fun findByTarget(target: ObjectId): List<Comment>
    fun findByTargetOrderByLikesDesc(target: ObjectId, limit: Int): List<Comment>
}