@file:Suppress("MemberVisibilityCanPrivate")

package ru.ifmo.ctddev.cookmate.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import java.util.*

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */

@TypeAlias("receipt")
data class Receipt(
        @Id val name: String,
        val rating: Int,
        val tools: String,
        val weight: String,
        val proteins: String,
        val fats: String,
        val carbohydrates: String,
        val calories: String,
        val totalTime: Long,
        val picture: String?,
        val steps: Array<ReceiptStep>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Receipt

        if (name != other.name) return false
        if (rating != other.rating) return false
        if (tools != other.tools) return false
        if (weight != other.weight) return false
        if (proteins != other.proteins) return false
        if (fats != other.fats) return false
        if (carbohydrates != other.carbohydrates) return false
        if (calories != other.calories) return false
        if (totalTime != other.totalTime) return false
        if (picture != other.picture) return false
        if (!Arrays.equals(steps, other.steps)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + rating
        result = 31 * result + tools.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + proteins.hashCode()
        result = 31 * result + fats.hashCode()
        result = 31 * result + carbohydrates.hashCode()
        result = 31 * result + calories.hashCode()
        result = 31 * result + totalTime.hashCode()
        result = 31 * result + (picture?.hashCode() ?: 0)
        result = 31 * result + Arrays.hashCode(steps)
        return result
    }
}

@TypeAlias("receiptStep")
data class ReceiptStep(
        @Id val stepId: Int,
        val shortDescription: String,
        val longDescription: String,
        val time: Long,
        val products: Array<Product>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReceiptStep

        if (stepId != other.stepId) return false
        if (shortDescription != other.shortDescription) return false
        if (longDescription != other.longDescription) return false
        if (time != other.time) return false
        if (!Arrays.equals(products, other.products)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = stepId
        result = 31 * result + shortDescription.hashCode()
        result = 31 * result + longDescription.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + Arrays.hashCode(products)
        return result
    }
}

@TypeAlias("product")
data class Product(
        @Id val name: String,
        val amount: String
)
