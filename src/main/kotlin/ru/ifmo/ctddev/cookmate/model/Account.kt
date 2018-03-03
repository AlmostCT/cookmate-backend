@file:Suppress("MemberVisibilityCanPrivate", "unused")

package ru.ifmo.ctddev.cookmate.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@TypeAlias("account")
data class Account(
        @Id val id: ObjectId,
        @Indexed(unique = true) val handle: String,
        val rating: Int,
        val avatar: String?,
        val achievements: List<Achievement>
)