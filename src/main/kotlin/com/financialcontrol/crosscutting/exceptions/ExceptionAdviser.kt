package com.financialcontrol.crosscutting.exceptions

import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionAdviser {

    @ExceptionHandler(BusinessException::class)
    fun handle(exception: BusinessException): ResponseEntity<ErrorMessageModel>{
        return ResponseEntity(
            ErrorMessageModel.create(exception.message, "Business Exception"),
            HttpStatus.BAD_REQUEST
        )
    }


    @ExceptionHandler(NotFoundException::class)
    fun handle(exception: NotFoundException): ResponseEntity<ErrorMessageModel> {
        return ResponseEntity(
            ErrorMessageModel.create(exception.message, "Not found error"),
            HttpStatus.NOT_FOUND
        )
    }
}