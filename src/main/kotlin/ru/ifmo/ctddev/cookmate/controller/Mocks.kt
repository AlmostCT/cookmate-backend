@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.bson.types.ObjectId
import ru.ifmo.ctddev.cookmate.model.*

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

// === Recipe === //

val milk = Product(ObjectId(), "Молоко", "500 мл")
val eggs = Product(ObjectId(), "Яйца", "3 шт.")
val flour = Product(ObjectId(),"Мука", "200 г")
val butter = Product(ObjectId(),"Масло сливочное", "30 г")
val sugar = Product(ObjectId(),"Сахар", "2 ст. ложки")
val salt = Product(ObjectId(),"Соль", "1/2 ч. ложки")

val step1 = RecipeStep(
        ObjectId(),
        1,
        "Взбейте яйца",
        "Взбейте яйца с сахаром, постепенно введите муку и соль.",
        0,
        listOf(eggs, sugar, flour, salt)
)

val step2 = RecipeStep(
        ObjectId(),
        stepId = 2,
        shortDescription = "Влейте молоко",
        longDescription = "Влейте молоко и аккуратно размешайте до однородной массы.",
        time = 0,
        products = listOf(milk)
)

val step3 = RecipeStep(
        ObjectId(),
        stepId = 3,
        shortDescription = "Оставьте на 20 минут",
        longDescription = "Оставьте на 20 минут.",
        time = 20 * 60,
        products = listOf()
)

val step4 = RecipeStep(
        ObjectId(),
        stepId = 4,
        shortDescription = "Добавьте растительное масло",
        longDescription = "Добавьте в тесто растительное масло и жарьте блины на сильно разогретой сковороде.",
        time = 0,
        products = listOf(butter)
)

val pancake = Recipe(
        ObjectId(),
        name = "Pancake",
        rating = 5,
        tools = "Миска, вилка, венчик и сковорода.",
        weight = "95.3 г",
        proteins = "24.8 г",
        fats = "25.7 г",
        carbohydrates = "95.3 г",
        calories = "709 ккал",
        totalTime = 40,
        picture = null,
        steps = listOf(step1, step2, step3, step4),
        userName = null,
        timeCreation = System.currentTimeMillis()
)

val userRecipe = Recipe(
        ObjectId(),
        name = "Pancake2",
        rating = 5,
        tools = "Миска, вилка, венчик и сковорода.",
        weight = "95.3 г",
        proteins = "24.8 г",
        fats = "25.7 г",
        carbohydrates = "95.3 г",
        calories = "709 ккал",
        totalTime = 40,
        picture = null,
        steps = listOf(step1, step2, step3, step4),
        userName = "User",
        timeCreation = System.currentTimeMillis()
)

// === Accounts === //

val vladimir = Account(
        id = ObjectId(),
        handle = "Vovan",
        avatar = null,
        rating = 4,
        achievements = listOf()
)

val pancakeAchievement = Achievement(
        name = "pancakeAchievement",
        description = "За лучшие блинчики в твоей жизни!",
        image = "pancakeAchievement.svg"
)

val vadim = Account(
        id = ObjectId(),
        handle = "Vadim",
        avatar = null,
        rating = 4,
        achievements = listOf(pancakeAchievement)
)

// === Comments === //

val comment = Comment(
        commentId = ObjectId(),
        account = vladimir,
        target = step4.id,
        text = "Сгорели блинчики, пока играл с котёнком :(",
        date = System.currentTimeMillis(),
        likes = 1,
        dislikes = 0
)

val comment1 = Comment(
        commentId = ObjectId(),
        account = vladimir,
        target = pancake.recipeId,
        text = "Со второго раза получились!",
        date = System.currentTimeMillis(),
        likes = 3,
        dislikes = 5
)

val comment2 = Comment(
        commentId = ObjectId(),
        account = vladimir,
        target = pancake.recipeId,
        text = "Со второго раза получились!",
        date = System.currentTimeMillis(),
        likes = 2,
        dislikes = 0
)
