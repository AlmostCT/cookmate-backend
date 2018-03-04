@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.service

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Recipe
import ru.ifmo.ctddev.cookmate.repo.RecipeRepository

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface RecipeService {
    fun getRecipe(recipeName: String): Recipe?
    fun getRecipe(id: ObjectId): Recipe?
    fun saveRecipe(recipe: Recipe): String
    fun getUserRecipes(topCount : Int, randomCount : Int): List<Recipe>
}

@Service("recipeService")
class RecipeServiceImpl : RecipeService {
    @Autowired
    private lateinit var recipeRepository: RecipeRepository

    override fun getRecipe(recipeName: String): Recipe? = recipeRepository.findByName(recipeName)

    override fun getRecipe(id: ObjectId): Recipe? = recipeRepository.findByRecipeId(id)

    override fun saveRecipe(recipe: Recipe): String {
        val result = recipeRepository.save(recipe)
        return "was saved success with ${result.recipeId} id\n ${recipe}"
    }

    override fun getUserRecipes(topCount : Int, randomCount : Int): List<Recipe> {
        val allUserRecipes = recipeRepository.findAllUsersRecipe().sortedByDescending { t -> t.timeCreation }
        val topRecipes = allUserRecipes.take(topCount).toMutableList()
        val otherRecipes = allUserRecipes.subList(0,  allUserRecipes.size)
        val randomIdx = (0 until otherRecipes.size).shuffled().take(randomCount)
        for (i in randomIdx) {
            topRecipes.add(otherRecipes[i])
        }
        return topRecipes
    }
}