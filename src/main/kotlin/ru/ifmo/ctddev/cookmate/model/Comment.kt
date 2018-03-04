@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.DBRef

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@TypeAlias("comment")
data class Comment(
        @Id @JsonSerialize(using = ToStringSerializer::class) val commentId: ObjectId?,
        @DBRef val account: Account,
        @JsonSerialize(using = ToStringSerializer::class) val target: ObjectId,
        val text: String,
        val date: Long,
        val likes: Int,
        val dislikes: Int,
        val stepId: Int
)