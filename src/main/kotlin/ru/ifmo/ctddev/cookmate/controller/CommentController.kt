@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Comment
import ru.ifmo.ctddev.cookmate.service.CommentService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class CommentController {
    @Autowired
    private lateinit var commentService: CommentService

    @GetMapping("/comments/all")
    fun getAllComments(@RequestParam target: ObjectId): List<Comment> =
            commentService.getAllComments(target)

    @GetMapping("/comments/top")
    fun getTopComments(@RequestParam target: ObjectId,
                       @RequestParam(required = false, defaultValue = "2") qty: Int): List<Comment> =
            commentService.getTopComments(target, qty)

    @PostMapping("/postComment")
    fun postComment(@RequestBody comment: Comment): String {
        commentService.saveComment(comment)
        return "Comment saved"
    }

    @GetMapping("postTestComment")
    fun postComment(): String = postComment(comment)
    @GetMapping("postTestComment1")
    fun postComment1(): String = postComment(comment1)
    @GetMapping("postTestComment2")
    fun postComment2(): String = postComment(comment2)
}