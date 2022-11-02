package com.financialcontrol.application.usecases.creditCard.create

import arrow.core.Either
import arrow.core.right
import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.application.resources.CreditCardDTO
import com.financialcontrol.domain.adapters.CreditCardAdapter
import org.springframework.stereotype.Service

@Service
class CreateCreditCardService(private val creditCardAdapter: CreditCardAdapter) {

    fun execute(request: CreateCreditCardDTO): Either<Throwable, CreditCardDTO> =
        Either.catch {
            kotlin.runCatching {
                creditCardAdapter.save(CreateCreditCardDTO.to(request)).let {
                    CreditCardDTO.of(it)
                }
            }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it.right() }
            .getOrThrow()
        }

}
