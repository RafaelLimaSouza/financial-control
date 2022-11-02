package com.financialcontrol.domain.adapters

import arrow.core.Option
import com.financialcontrol.domain.models.CreditCard
import com.financialcontrol.infrastructure.entities.CreditCardEntity
import java.util.UUID

interface CreditCardAdapter {
    fun save(creditCard: CreditCard): CreditCard

    fun findAll(): List<CreditCard>

    fun findById(id: UUID): Option<CreditCard>

    fun delete(creditCard: CreditCardEntity)
}