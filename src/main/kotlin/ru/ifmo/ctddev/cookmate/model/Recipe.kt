@file:Suppress("MemberVisibilityCanPrivate")

package ru.ifmo.ctddev.cookmate.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

@TypeAlias("recipe")
data class Recipe(
        @Id @JsonSerialize(using = ToStringSerializer::class) val recipeId: ObjectId?,
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
        val ingredients: List<Product>,
        val steps: List<RecipeStep>
)

@TypeAlias("recipeStep")
data class RecipeStep(
        @Id @JsonSerialize(using = ToStringSerializer::class) val id : ObjectId?,
        val stepId: Int,
        val shortDescription: String,
        val longDescription: String,
        val time: Long,
        val products: List<Product>
)

@TypeAlias("product")
data class Product (
        @Id @JsonSerialize(using = ToStringSerializer::class) val id : ObjectId?,
        val name: String,
        val amount: String
)
