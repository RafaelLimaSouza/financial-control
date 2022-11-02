package com.financialcontrol.application.resources

import com.financialcontrol.domain.models.CreditCard
import javax.validation.constraints.NotBlank

data class CreateCreditCardDTO(
    @NotBlank
    val number: Long,
    @NotBlank
    val flag: String
){

    companion object {
        fun to(request :CreateCreditCardDTO): CreditCard = CreditCard(
            number = request.number,
            flag = request.flag
        )
    }
}
