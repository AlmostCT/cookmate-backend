@file:Suppress("unused", "MemberVisibilityCanPrivate")

package ru.ifmo.ctddev.cookmate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Product
import ru.ifmo.ctddev.cookmate.model.Receipt
import ru.ifmo.ctddev.cookmate.model.ReceiptStep
import ru.ifmo.ctddev.cookmate.service.ReceiptService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class ReceiptController {
    @Autowired
    lateinit var receiptService: ReceiptService

    @GetMapping("/receipts/{name}")
    fun getReceipt(@PathVariable name: String): Receipt? = receiptService.getReceipt(name)

    @PostMapping("/saveReceipt")
    fun saveReceipt(@RequestBody receipt: Receipt): String = receiptService.saveReceipt(receipt)

    // TODO: REMOVE
    @GetMapping("/saveTest")
    fun saveTest(): String = saveReceipt(pancake)

    // mocks TODO: remove
    companion object {
        private val milk = Product("Молоко", "500 мл")
        private val eggs = Product("Яйца", "3 шт.")
        private val flour = Product("Мука", "200 г")
        private val butter = Product("Масло сливочное", "30 г")
        private val sugar = Product("Сахар", "2 ст. ложки")
        private val salt = Product("Соль", "1/2 ч. ложки")

        private val step1 = ReceiptStep(
                1,
                "Взбейте яйца",
                "Взбейте яйца с сахаром, постепенно введите муку и соль.",
                0,
                arrayOf(eggs, sugar, flour, salt)
        )

        private val step2 = ReceiptStep(
                stepId = 2,
                shortDescription = "Влейте молоко",
                longDescription = "Влейте молоко и аккуратно размешайте до однородной массы.",
                time = 0,
                products = arrayOf(milk)
        )

        private val step3 = ReceiptStep(
                stepId = 3,
                shortDescription = "Оставьте на 20 минут",
                longDescription = "Оставьте на 20 минут.",
                time = 20 * 60,
                products = arrayOf()
        )

        private val step4 = ReceiptStep(
                stepId = 4,
                shortDescription = "Добавьте растительное масло",
                longDescription = "Добавьте в тесто растительное масло и жарьте блины на сильно разогретой сковороде.",
                time = 0,
                products = arrayOf(butter)
        )

        private val pancake = Receipt(
                name = "Pancake",
                rating = 5,
                tools = "Миска, вилка, венчик и сковорода.",
                weight = "95.3 г",
                proteins = "24.8 г",
                fats = "25.7 г",
                carbohydrates = "95.3 г",
                calories = "709 ккал",
                totalTime = 40,
                picture = null,
                steps = arrayOf(step1, step2, step3, step4)
        )
    }
}