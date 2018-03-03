@file:Suppress("MemberVisibilityCanPrivate")

package ru.ifmo.ctddev.cookmate.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

@TypeAlias("recipe")
data class Recipe(
        @Id val recipeId: ObjectId,
        val timeCreation : Long,
        val userName: String?,
        val name: String,
        val rating: Int,
        val tools: String,
        val weight: String,
        val proteins: String,
        val fats: String,
        val carbohydrates: String,
        val calories: String,
        val totalTime: Long,
        val picture: String?,
        val steps: List<RecipeStep>
)

@TypeAlias("recipeStep")
data class RecipeStep(
        @Id val id : ObjectId,
        val stepId: Int,
        val shortDescription: String,
        val longDescription: String,
        val time: Long,
        val products: List<Product>
)

@TypeAlias("product")
data class Product (
        @Id val id : ObjectId,
        val name: String,
        val amount: String
)
