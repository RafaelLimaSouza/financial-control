package com.financialcontrol.domain.adapters

import com.financialcontrol.domain.models.Category

interface CategoryAdapter {
    fun save(category: Category): Category

    fun findAll(): List<Category>
}