@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Recipe
import ru.ifmo.ctddev.cookmate.repo.RecipeRepository

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface RecipeService {
    fun getRecipe(recipeName: String): Recipe?
    fun saveRecipe(recipe: Recipe): String
}

@Service("recipeService")
class RecipeServiceImpl : RecipeService {
    @Autowired
    private lateinit var recipeRepository: RecipeRepository

    override fun getRecipe(recipeName: String): Recipe? = recipeRepository.findByName(recipeName)

    override fun saveRecipe(recipe: Recipe): String {
        recipeRepository.save(recipe)
        return "Recipe '${recipe.name}' saved"
    }
}