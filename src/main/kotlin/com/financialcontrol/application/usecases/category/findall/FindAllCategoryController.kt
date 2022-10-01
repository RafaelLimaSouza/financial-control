package com.financialcontrol.application.usecases.category.findall

import com.financialcontrol.application.resources.CategoryDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("category")
@RestController
class FindAllCategoryController(private val findAllCategoryService: FindAllCategoryService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<CategoryDTO>> =
        ResponseEntity(findAllCategoryService.execute(), HttpStatus.OK)
}