@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.ctddev.cookmate.service.RecipeService
import ru.ifmo.ctddev.cookmate.service.RecognitionService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class RecognitionController {
    @Autowired
    private lateinit var recognitionService: RecognitionService

    @Autowired
    private lateinit var recipeService: RecipeService

    @GetMapping("/recognize")
    fun recognize(@RequestParam recipeId : ObjectId, @RequestParam stepId : Int, @RequestParam text: String): String {

        val recipe = recipeService.getRecipe(recipeId)
        if (recipe == null) {
            return ""
        } else {
            val recipeStep = recipe.steps.find { t -> t.stepId == stepId }
            if (recipeStep == null) {
                return ""
            } else {
                return recognitionService.recognize(recipeStep, text)
            }
        }
    }
}