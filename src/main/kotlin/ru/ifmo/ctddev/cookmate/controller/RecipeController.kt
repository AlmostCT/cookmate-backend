@file:Suppress("unused", "MemberVisibilityCanPrivate")

package ru.ifmo.ctddev.cookmate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Recipe
import ru.ifmo.ctddev.cookmate.service.RecipeService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class RecipeController {
    @Autowired
    private lateinit var recipeService: RecipeService

    @GetMapping("/recipes/{name}")
    fun getRecipe(@PathVariable name: String): Recipe? = recipeService.getRecipe(name)

    @GetMapping("/usersRecipes")
    fun getUsersRecipes(@RequestParam topCount: Int, @RequestParam randomCount : Int): List<Recipe> =
            recipeService.getUsersRecipes(topCount, randomCount)

    @PostMapping("/saveRecipe")
    fun saveRecipe(@RequestBody recipe: Recipe): String = recipeService.saveRecipe(recipe)

    @PostMapping("/saveListRecipe")
    fun saveListRecipe(@RequestBody recipes: Array<Recipe>): String {
        for (recipe in recipes) {
            recipeService.saveRecipe(recipe)
        }
        return "success"
    }
}