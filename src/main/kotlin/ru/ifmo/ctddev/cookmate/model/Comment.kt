@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.DBRef

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@TypeAlias("comment")
data class Comment(
        @Id val commentId: ObjectId?,
        @DBRef val account: Account,
        val target: ObjectId,
        val text: String,
        val date: Long,
        val likes: Int,
        val dislikes: Int
)