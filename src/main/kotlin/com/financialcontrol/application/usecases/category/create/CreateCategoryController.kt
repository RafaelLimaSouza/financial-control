package com.financialcontrol.application.usecases.category.create

import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("category")
class CreateCategoryController(
    private val createCategoryService: CreateCategoryService
) {

    @PostMapping
    fun create(@RequestBody @Valid request: CreateCategoryDTO): ResponseEntity<CategoryDTO> =
        createCategoryService.execute(request).fold(
            {throw it},
            {ResponseEntity(it, HttpStatus.CREATED)}
        )
}