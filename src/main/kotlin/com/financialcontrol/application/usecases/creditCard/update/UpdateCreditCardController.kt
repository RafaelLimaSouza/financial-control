package com.financialcontrol.application.usecases.creditCard.update

import com.financialcontrol.application.resources.CreateCreditCardDTO
import com.financialcontrol.application.resources.CreditCardDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("credit-card")
class UpdateCreditCardController(private val updateCreditCardService: UpdateCreditCardService) {

    @PutMapping("{id}")
    fun update(
        @PathVariable(required = true) id: UUID,
        @Valid @RequestBody request: CreateCreditCardDTO
    ): ResponseEntity<CreditCardDTO> =
        updateCreditCardService.execute(id, request).fold(
            { throw it },
            { ResponseEntity(it, HttpStatus.NO_CONTENT) }
        )
}