@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Comment
import ru.ifmo.ctddev.cookmate.service.AccountService
import ru.ifmo.ctddev.cookmate.service.CommentService
import ru.ifmo.ctddev.cookmate.service.RecipeService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class CommentController {
    @Autowired
    private lateinit var commentService: CommentService

    @GetMapping("/comments/all")
    fun getAllComments(@RequestParam target: ObjectId,
                       @RequestParam(required = false, defaultValue = "-1") step: Int): List<Comment> =
            commentService.getAllComments(target, step)

    @GetMapping("/comments/top")
    fun getTopComments(@RequestParam target: ObjectId,
                       @RequestParam(required = false, defaultValue = "-1") step: Int,
                       @RequestParam(required = false, defaultValue = "2") qty: Int): List<Comment> =
            commentService.getTopComments(target, step, qty)

    @PostMapping("/postComment")
    fun postComment(@RequestBody comment: Comment): String {
        commentService.saveComment(comment)
        return "Comment saved"
    }


    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var recipeService: RecipeService




    @GetMapping("/postTestComment")
    fun postTestComment(): String  {
        val comment3 = Comment(
                commentId = null,
                account = accountService.getAccount(ObjectId("5a9b7a113cfe433b705e124f"))!!,
                target = recipeService.getRecipe(ObjectId("5a9b7b4c3cfe43439867770e"))!!.recipeId!!,
                text = "Добавил три щепотки соли и улетел!",
                date = System.currentTimeMillis(),
                likes =  192,
                dislikes = 10,
                stepId = 1
        )
        postComment(comment3)
        return "success"
    }
}