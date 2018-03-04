@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.AddCommentRequest
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

    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var recipeService: RecipeService

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
    fun postComment(@RequestBody commentRequest: AddCommentRequest): String {
        val comment = Comment(null, accountService.getAccount(commentRequest.accountId)!!, commentRequest.recipeId, commentRequest.text,
                System.currentTimeMillis(), 0, 0, commentRequest.step)
        return commentService.saveComment(comment)
        return "Success"
    }

    fun postComment(comment : Comment): String {
        return commentService.saveComment(comment)
        return "Success"
    }





    @GetMapping("/postTestComment")
    fun postTestComment(): String  {

        val comment2 = Comment(
                commentId = null,
                account = accountService.getAccount(ObjectId("5a9b7a113cfe433b705e1250"))!!,
                target = recipeService.getRecipe(ObjectId("5a9b7b4c3cfe43439867770e"))!!.recipeId!!,
                text = "Чумачечий суп!",
                date = System.currentTimeMillis(),
                likes =  52,
                dislikes = 1,
                stepId = -1
        )

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
        postComment(comment2)
        return "success"
    }
}