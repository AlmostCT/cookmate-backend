@file:Suppress("MemberVisibilityCanPrivate", "unused")

package ru.ifmo.ctddev.cookmate.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@TypeAlias("account")
data class Account(
        @Id @JsonSerialize(using = ToStringSerializer::class) val id: ObjectId?,
        @Indexed(unique = true) val handle: String,
        val rating: Int,
        val avatar: String?,
        @DBRef val achievements: List<Achievement>
)