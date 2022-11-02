package com.financialcontrol.application.resources

import com.financialcontrol.domain.models.CreditCard
import java.util.UUID

data class CreditCardDTO(
    val id: UUID,
    val number: Long,
    val flag: String,
    val enabled: Boolean? = true,
){
    companion object {
        fun of(creditCard: CreditCard) = CreditCardDTO(
            id = creditCard.id,
            number = creditCard.number,
            flag = creditCard.flag,
            enabled = creditCard.enabled
        )
    }
}
