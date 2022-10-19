package com.financialcontrol.application.usecases.category.findone

import com.financialcontrol.application.resources.CategoryDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("category")
class FindOneCategoryController (private val findOneCategoryService: FindOneCategoryService){

    @GetMapping("{id}")
    fun findOne(@PathVariable("id") id: UUID): ResponseEntity<CategoryDTO> =
        findOneCategoryService.execute(id).fold(
            { throw it },
            { ResponseEntity(it, HttpStatus.OK) }
        )
}