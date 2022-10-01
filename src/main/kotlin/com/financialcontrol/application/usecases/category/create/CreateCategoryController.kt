package com.financialcontrol.application.usecases.category.create

import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("category")
class CreateCategoryController(
    private val categoryService: CreateCategoryService
) {

    @PostMapping
    fun create(@RequestBody @Valid request: CreateCategoryDTO): ResponseEntity<CategoryDTO> =
        ResponseEntity(categoryService.execute(request), HttpStatus.CREATED)
}