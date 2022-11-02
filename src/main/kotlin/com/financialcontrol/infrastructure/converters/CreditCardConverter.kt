package com.financialcontrol.infrastructure.converters

import com.financialcontrol.domain.models.CreditCard
import com.financialcontrol.infrastructure.entities.CreditCardEntity

object CreditCardConverter {
    fun of(creditCard: CreditCard) = CreditCardEntity(
        id = creditCard.id,
        number = creditCard.number,
        flag = creditCard.flag,
        enabled = creditCard.enabled,
        createdAt = creditCard.createdAt,
        updatedAt = creditCard.updatedAt
    )

    fun of(creditCard: CreditCardEntity) = CreditCard(
        id = creditCard.id,
        number = creditCard.number,
        flag = creditCard.flag,
        enabled = creditCard.enabled,
        createdAt = creditCard.createdAt,
        updatedAt = creditCard.updatedAt
    )
}