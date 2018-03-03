package ru.ifmo.ctddev.cookmate.repo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import ru.ifmo.ctddev.cookmate.model.Recipe

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

interface RecipeRepository : MongoRepository<Recipe, ObjectId> {
    fun findByName(name: String): Recipe?
}