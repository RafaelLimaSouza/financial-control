package com.financialcontrol.application.usecases.creditCard.delete

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import com.financialcontrol.crosscutting.exceptions.NotFoundException
import com.financialcontrol.domain.adapters.CreditCardAdapter
import org.springframework.stereotype.Service
import java.util.UUID
import com.financialcontrol.infrastructure.converters.CreditCardConverter.of


@Service
class DeleteCreditCardService(private val creditCardAdapter: CreditCardAdapter) {

    fun execute(id: UUID): Either<Throwable, Unit> =
        Either.catch {
            kotlin.runCatching {
                creditCardAdapter.findById(id).map { creditCard ->
                    creditCardAdapter.delete(of(creditCard))
                }.getOrElse { throw NotFoundException.of("CreditCard", id) }
            }
            .onFailure { throw it }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}