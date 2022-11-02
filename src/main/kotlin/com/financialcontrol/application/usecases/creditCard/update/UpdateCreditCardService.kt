package com.financialcontrol.application.usecases.creditCard.update

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.application.resources.CreditCardDTO
import com.financialcontrol.crosscutting.exceptions.NotFoundException
import com.financialcontrol.domain.adapters.CreditCardAdapter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateCreditCardService(private val creditCardAdapter: CreditCardAdapter) {

    fun execute(id: UUID, request: CreateCreditCardDTO): Either<Throwable, CreditCardDTO> =
        Either.catch {
            kotlin.runCatching {
                creditCardAdapter.findById(id).map { entity ->
                    val newCreditCard = entity.copy(
                        number = request.number,
                        flag = request.flag
                    )
                    creditCardAdapter.save(newCreditCard).let {
                        CreditCardDTO.of(it)
                    }
                }.getOrElse { throw NotFoundException.of("CreditCard", id) }
            }
            .onFailure { throw it }
            .onSuccess { it.right() }
            .getOrThrow()
        }
}