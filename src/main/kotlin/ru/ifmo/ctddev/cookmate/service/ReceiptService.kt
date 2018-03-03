@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Receipt
import ru.ifmo.ctddev.cookmate.repo.ReceiptRepository

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface ReceiptService {
    fun getReceipt(receiptName: String): Receipt?
    fun saveReceipt(receipt: Receipt): String
}

@Service("receiptService")
class ReceiptServiceImpl: ReceiptService {
    @Autowired
    private lateinit var receiptRepository: ReceiptRepository

    override fun getReceipt(receiptName: String): Receipt? = receiptRepository.findReceiptByName(receiptName)

    override fun saveReceipt(receipt: Receipt): String {
        receiptRepository.save(receipt)
        return "Receipt '${receipt.name}' saved"
    }
}