package ru.ifmo.ctddev.cookmate.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@TypeAlias("achievement")
data class Achievement(
        @Id val name: String,
        val description: String,
        val image: String
)