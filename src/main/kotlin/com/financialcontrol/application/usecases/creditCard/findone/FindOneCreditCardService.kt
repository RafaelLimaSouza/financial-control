package com.financialcontrol.application.usecases.creditCard.findone

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import com.financialcontrol.application.resources.CreditCardDTO
import com.financialcontrol.crosscutting.exceptions.NotFoundException
import com.financialcontrol.domain.adapters.CreditCardAdapter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FindOneCreditCardService(private val creditCardAdapter: CreditCardAdapter) {

    fun execute(id: UUID): Either<Throwable, CreditCardDTO> =
        Either.catch {
            kotlin.runCatching {
                creditCardAdapter.findById(id).map {
                    CreditCardDTO.of(it)
                }.getOrElse { throw NotFoundException.of("CreditCard", id) }
            }
            .onFailure { throw it }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}