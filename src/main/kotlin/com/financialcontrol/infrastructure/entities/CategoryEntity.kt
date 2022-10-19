package com.financialcontrol.infrastructure.entities


import com.financialcontrol.domain.enums.TypeEnum
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*


@Entity
@Table(name = "category")
data class CategoryEntity(

    @Id
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "uuid")
    val id: UUID,

    @Column
    val name: String,

    @Column
    val enabled: Boolean? = true,

    @Column
    @Enumerated(EnumType.STRING)
    val type: TypeEnum,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    val updatedAt: LocalDateTime? = null
){}