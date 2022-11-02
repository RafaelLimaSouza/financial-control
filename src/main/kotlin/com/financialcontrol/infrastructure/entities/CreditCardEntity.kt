package com.financialcontrol.infrastructure.entities

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "credit_card")
data class CreditCardEntity(
    @Id
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "uuid")
    val id: UUID,

    @Column(nullable = false, unique = true)
    val number: Long,

    @Column(nullable = false)
    val flag: String,

    @Column
    val enabled: Boolean? = true,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, )
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    val updatedAt: LocalDateTime? = null
){}
