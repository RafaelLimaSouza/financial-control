package com.financialcontrol.infrastructure.adapters

import arrow.core.Option
import com.financialcontrol.crosscutting.extensions.toOption
import com.financialcontrol.domain.adapters.CreditCardAdapter
import com.financialcontrol.domain.models.CreditCard
import com.financialcontrol.infrastructure.repositories.CreditCardRepository
import com.financialcontrol.infrastructure.converters.CreditCardConverter
import com.financialcontrol.infrastructure.entities.CreditCardEntity
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CreditCardAdapterImpl(private val creditCardRepository: CreditCardRepository): CreditCardAdapter {
    override fun save(creditCard: CreditCard) = CreditCardConverter.of(
        creditCardRepository.save(CreditCardConverter.of(creditCard))
    )

    override fun findAll(): List<CreditCard> =
        creditCardRepository.findAll().map {
            CreditCardConverter.of(it)
        }

    override fun findById(id: UUID): Option<CreditCard> =
        creditCardRepository.findById(id).map { CreditCardConverter.of(it) }.toOption()

    override fun delete(creditCard: CreditCardEntity) =
        creditCardRepository.delete(creditCard)

}