package com.financialcontrol.application.usecases.creditCard.findone

import com.financialcontrol.application.resources.CreditCardDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("credit-card")
class FindOneCreditCardController(private val findOneCreditCardService:FindOneCreditCardService) {

    @GetMapping("{id}")
    fun findOne(@PathVariable(required = true) id: UUID): ResponseEntity<CreditCardDTO> =
        findOneCreditCardService.execute(id).fold(
            { throw it },
            { ResponseEntity(it, HttpStatus.OK) }
        )
}