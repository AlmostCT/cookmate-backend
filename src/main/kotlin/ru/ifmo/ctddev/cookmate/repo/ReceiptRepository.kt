package ru.ifmo.ctddev.cookmate.repo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.ifmo.ctddev.cookmate.model.Receipt

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

interface ReceiptRepository: MongoRepository<Receipt, String> {
    fun findReceiptByName(receiptName: String): Receipt? {
        val receipt = findById(receiptName)
        return if (receipt.isPresent) receipt.get() else null
    }
}