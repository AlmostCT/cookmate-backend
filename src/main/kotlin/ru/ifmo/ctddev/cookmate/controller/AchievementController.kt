package ru.ifmo.ctddev.cookmate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.ifmo.ctddev.cookmate.model.Account
import ru.ifmo.ctddev.cookmate.model.Achievement
import ru.ifmo.ctddev.cookmate.service.AccountService
import ru.ifmo.ctddev.cookmate.service.AchievementService

@RestController
class AchievementController {
    @Autowired
    private lateinit var achievementService: AchievementService

    @GetMapping("/achievements/{name}")
    fun getAccount(@PathVariable name: String): Achievement? = achievementService.getAchievement(name)

    @PostMapping("/saveAchievement")
    fun saveAchievement(@RequestBody achievement: Achievement): String = achievementService.saveAchievement(achievement)

    @GetMapping("/saveTestAchievement")
    fun saveTestAccount(): String = saveAchievement(pancakeAchievement)
}