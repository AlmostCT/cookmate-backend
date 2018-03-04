package ru.ifmo.ctddev.cookmate.repo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.ifmo.ctddev.cookmate.model.Recipe

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

interface RecipeRepository : MongoRepository<Recipe, ObjectId> {
    fun findByName(name: String): Recipe?

    @Query("{'userName' : {\$ne : null}}")
    fun findAllUsersRecipe() : List<Recipe>

    fun findByRecipeId(id: ObjectId): Recipe?
}