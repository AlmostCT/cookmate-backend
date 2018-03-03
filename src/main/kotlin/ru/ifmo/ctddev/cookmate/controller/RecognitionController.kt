package ru.ifmo.ctddev.cookmate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.ifmo.ctddev.cookmate.service.ReceiptService
import ru.ifmo.ctddev.cookmate.service.RecognitionService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class RecognitionController {
    @Autowired
    lateinit var recognitionService: RecognitionService


    @GetMapping("/recognize")
    fun recognize(@RequestParam string: String): Int {
        return recognitionService.recognize(string)
    }


}