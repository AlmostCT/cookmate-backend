package ru.ifmo.ctddev.cookmate.repo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.ifmo.ctddev.cookmate.model.Achievement

interface AchievementRepository : MongoRepository<Achievement, String> {
    fun findByName(name: String): Achievement?
}