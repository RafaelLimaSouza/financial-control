package com.financialcontrol.application.usecases.category.update

import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("category")
class UpdateCategoryController(
    private val categoryService: UpdateCategoryService
) {

    @PutMapping("/{id}")
    fun create(
        @PathVariable(required = true) id: UUID,
        @Valid @RequestBody request: CreateCategoryDTO): ResponseEntity<CategoryDTO> =
        categoryService.execute(id, request).fold(
            {throw it},
            {ResponseEntity(it, HttpStatus.NO_CONTENT)}
        )

}