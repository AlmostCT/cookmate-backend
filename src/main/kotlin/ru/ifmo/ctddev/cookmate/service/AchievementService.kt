package ru.ifmo.ctddev.cookmate.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Account
import ru.ifmo.ctddev.cookmate.model.Achievement
import ru.ifmo.ctddev.cookmate.repo.AccountRepository
import ru.ifmo.ctddev.cookmate.repo.AchievementRepository

interface AchievementService {
    fun getAchievement(name: String): Achievement?
    fun saveAchievement(achievement: Achievement): String
}

@Service("achievementService")
class AchievementServiceImpl : AchievementService {
    @Autowired
    private lateinit var achievementRepository: AchievementRepository

    override fun getAchievement(name: String): Achievement? {
        return achievementRepository.findByName(name)
    }


    override fun saveAchievement(achievement: Achievement): String {
        achievementRepository.save(achievement)
        return "Account ${achievement.name} saved"
    }

}