package com.financialcontrol.infrastructure.repositories

import com.financialcontrol.domain.enums.TypeEnum
import com.financialcontrol.infrastructure.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, UUID> {

    fun findByType(type: TypeEnum): List<CategoryEntity>
}