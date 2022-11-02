package com.financialcontrol.infrastructure.repositories

import com.financialcontrol.infrastructure.entities.CreditCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CreditCardRepository: JpaRepository<CreditCardEntity, UUID> {
}