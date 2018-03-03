@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Account
import ru.ifmo.ctddev.cookmate.service.AccountService

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
@RestController
class AccountController {
    @Autowired
    private lateinit var accountService: AccountService

    @GetMapping("/accounts/{handle}")
    fun getAccount(@PathVariable handle: String): Account? = accountService.getAccount(handle)

    @PostMapping("/saveAccount")
    fun saveAccount(@RequestBody account: Account): String = accountService.saveAccount(account)

    @GetMapping("/saveTestAccount")
    fun saveTestAccount(): String = saveAccount(vladimir)

    @GetMapping("/saveTestAccount2")
    fun saveTestAccount2(): String = saveAccount(vadim)
}