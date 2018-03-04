@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.service

import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Account
import ru.ifmo.ctddev.cookmate.repo.AccountRepository

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface AccountService {
    fun getAccount(handle: String): Account?
    fun getAccount(id: ObjectId): Account?
    fun saveAccount(account: Account): String
}

@Service("accountService")
class AccountServiceImpl : AccountService {


    @Autowired
    private lateinit var accountRepository: AccountRepository

    override fun getAccount(handle: String): Account? {
        log.info("getAccount($handle)")
        val result = accountRepository.findByHandle(handle)
        log.info("found: $result")
        return result
    }

    override fun getAccount(id: ObjectId): Account? {
        val result = accountRepository.findById(id)
        return result
    }

    override fun saveAccount(account: Account): String {
        accountRepository.save(account)
        return "Account ${account.handle} saved"
    }

    companion object {
        val log = LoggerFactory.getLogger(AccountServiceImpl::class.java)!!
    }
}