package com.financialcontrol.application.usecases.creditCard.create

import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.application.resources.CreditCardDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("credit-card")
class CreateCreditCardController(private val createCreditCardService: CreateCreditCardService) {

    @PostMapping
    fun create(@Valid @RequestBody request: CreateCreditCardDTO): ResponseEntity<CreditCardDTO> =
        createCreditCardService.execute(request).fold(
            { throw it },
            { ResponseEntity(it, HttpStatus.CREATED) }
        )
}