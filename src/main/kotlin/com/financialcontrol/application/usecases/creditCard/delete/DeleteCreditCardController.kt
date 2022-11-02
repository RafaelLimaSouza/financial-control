package com.financialcontrol.application.usecases.creditCard.delete

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("credit-card")
class DeleteCreditCardController(private val deleteCreditCardService:DeleteCreditCardService) {

    @DeleteMapping("{id}")
    fun delete(@PathVariable(required = true) id: UUID): ResponseEntity<Unit>  =
        deleteCreditCardService.execute(id).fold(
            { throw it },
            { ResponseEntity(it, HttpStatus.NO_CONTENT) }
        )
}