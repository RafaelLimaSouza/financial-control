package com.financialcontrol.application.usecases.creditCard.findall

import com.financialcontrol.application.resources.CreditCardDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("credit-card")
class FindAllCreditCardController(private val findAllCreditCardService: FindAllCreditCardService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<CreditCardDTO>> =
        findAllCreditCardService.execute().fold(
            { throw it },
            { ResponseEntity(it, HttpStatus.OK) }
        )
}