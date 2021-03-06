package ru.ifmo.ctddev.cookmate.repo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import ru.ifmo.ctddev.cookmate.model.Account

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface AccountRepository : MongoRepository<Account, String> {
    fun findByHandle(handle: String): Account?
    fun findById(id: ObjectId): Account?
}