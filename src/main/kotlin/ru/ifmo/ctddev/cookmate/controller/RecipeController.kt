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

    @PostMapping("/saveRecipe")
    fun saveRecipe(@RequestBody recipe: Recipe): String = recipeService.saveRecipe(recipe)

    // TODO: REMOVE
    @GetMapping("/saveTestRecipe")
    fun saveTest(): String = saveRecipe(pancake)
}