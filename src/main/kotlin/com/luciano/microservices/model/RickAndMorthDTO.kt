package com.luciano.microservices.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_person")
data class RickAndMorth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique=true)
    val name: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String?
)
