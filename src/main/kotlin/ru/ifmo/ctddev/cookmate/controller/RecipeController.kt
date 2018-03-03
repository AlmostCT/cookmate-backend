@file:Suppress("unused", "MemberVisibilityCanPrivate")

package ru.ifmo.ctddev.cookmate.controller

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Product
import ru.ifmo.ctddev.cookmate.model.Recipe
import ru.ifmo.ctddev.cookmate.model.RecipeStep
import ru.ifmo.ctddev.cookmate.service.RecipeService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class RecipeController {
    @Autowired
    lateinit var recipeService: RecipeService

    @GetMapping("/recipes/{name}")
    fun getRecipe(@PathVariable name: String): Recipe? = recipeService.getRecipe(name)

    @GetMapping("/userRecipes")
    fun getUserRecipes(@RequestParam topCount: Int, @RequestParam randomCount : Int):
            List<Recipe> = recipeService.getUserRecipes(topCount, randomCount)

    @PostMapping("/saveRecipe")
    fun saveRecipe(@RequestBody recipe: Recipe): String = recipeService.saveRecipe(recipe)


    // TODO: REMOVE
    @GetMapping("/saveTest")
    fun saveTest(): String = saveRecipe(pancake)

    // TODO: REMOVE
    @GetMapping("/saveTest2")
    fun saveTest2(): String = saveRecipe(userRecipe)

    // mocks TODO: remove
    companion object {
        private val milk = Product(ObjectId(), "Молоко", "500 мл")
        private val eggs = Product(ObjectId(), "Яйца", "3 шт.")
        private val flour = Product(ObjectId(),"Мука", "200 г")
        private val butter = Product(ObjectId(),"Масло сливочное", "30 г")
        private val sugar = Product(ObjectId(),"Сахар", "2 ст. ложки")
        private val salt = Product(ObjectId(),"Соль", "1/2 ч. ложки")

        private val step1 = RecipeStep(
                 ObjectId(),
                1,
                "Взбейте яйца",
                "Взбейте яйца с сахаром, постепенно введите муку и соль.",
                0,
                listOf(eggs, sugar, flour, salt)
        )

        private val step2 = RecipeStep(
                ObjectId(),
                stepId = 2,
                shortDescription = "Влейте молоко",
                longDescription = "Влейте молоко и аккуратно размешайте до однородной массы.",
                time = 0,
                products = listOf(milk)
        )

        private val step3 = RecipeStep(
                ObjectId(),
                stepId = 3,
                shortDescription = "Оставьте на 20 минут",
                longDescription = "Оставьте на 20 минут.",
                time = 20 * 60,
                products = listOf()
        )

        private val step4 = RecipeStep(
                ObjectId(),
                stepId = 4,
                shortDescription = "Добавьте растительное масло",
                longDescription = "Добавьте в тесто растительное масло и жарьте блины на сильно разогретой сковороде.",
                time = 0,
                products = listOf(butter)
        )

        private val pancake = Recipe(
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

        private val userRecipe = Recipe(
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
    }
}