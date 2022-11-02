package com.financialcontrol.application.resources

class CreateCreditCardDTOBuilder {

    var number: Long = 12344567890123456

    var flag: String = "VISA"

    fun build() = CreateCreditCardDTO(
        number = number,
        flag = flag
    )
}