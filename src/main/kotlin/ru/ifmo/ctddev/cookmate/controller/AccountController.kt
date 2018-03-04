@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Account
import ru.ifmo.ctddev.cookmate.model.Achievement
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

    @PostMapping("/saveListAccount")
    fun saveListAccount(@RequestBody accounts: Array<Account>): String {
        for (account in accounts) {
            accountService.saveAccount(account)
        }
        return "success"
    }
}