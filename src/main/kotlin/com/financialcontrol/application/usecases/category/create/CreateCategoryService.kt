package com.financialcontrol.application.usecases.category.create

import com.financialcontrol.application.resources.CategoryDTO
import com.financialcontrol.application.resources.CreateCategoryDTO
import com.financialcontrol.infrastructure.converters.CategoryConverter
import com.financialcontrol.infrastructure.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CreateCategoryService(private val categoryRepository: CategoryRepository) {

    fun execute(request: CreateCategoryDTO) =
        kotlin
            .runCatching {
                categoryRepository.save(CategoryConverter.of(CreateCategoryDTO.to(request))).let {
                    CategoryDTO(id = it.id)
                }
            }
            .onFailure { throw Exception(it.localizedMessage) }
            .onSuccess { it }
            .getOrThrow()

}
