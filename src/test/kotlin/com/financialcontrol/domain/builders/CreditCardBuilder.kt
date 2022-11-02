package com.financialcontrol.domain.builders

import com.financialcontrol.domain.models.CreditCard
import java.time.LocalDateTime
import java.util.UUID

class CreditCardBuilder {
    var id: UUID = UUID.randomUUID()

    var number: Long = 1234567890123456

    var flag: String = "VISA"

    var enabled: Boolean = true

    var createdAt: LocalDateTime = LocalDateTime.now()

    var updatedAt: LocalDateTime = LocalDateTime.now()

    fun build() = CreditCard(
        id = id,
        number = number,
        flag = flag,
        enabled = enabled,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}