package com.financialcontrol.application.usecases.creditCard.findall

import arrow.core.Either
import arrow.core.right
import com.financialcontrol.application.resources.CreditCardDTO
import com.financialcontrol.domain.adapters.CreditCardAdapter
import org.springframework.stereotype.Service

@Service
class FindAllCreditCardService(private val creditCardAdapter: CreditCardAdapter) {

    fun execute(): Either<Throwable, List<CreditCardDTO>> =
        Either.catch {
            kotlin.runCatching {
                creditCardAdapter.findAll().map {
                    CreditCardDTO.of(it)
                }
            }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}