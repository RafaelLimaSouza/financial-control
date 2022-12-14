package com.financialcontrol.domain.models

import java.time.LocalDateTime
import java.util.UUID

data class Category(

    val id: UUID = UUID.randomUUID(),

    val name: String,

    val enabled: Boolean? = true,

    val createAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime? = null
){}

