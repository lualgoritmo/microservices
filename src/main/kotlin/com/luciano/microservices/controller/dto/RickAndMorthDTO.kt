package com.luciano.microservices.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.luciano.microservices.model.RickAndMorth

@JsonIgnoreProperties(ignoreUnknown = true)
data class RickMorthResponse(
    val results: List<RickAndMorthDTO>
)
data class Origin(
    val name:String,
    val url: String
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class RickAndMorthDTO(
    val id:Long?,
    val name: String,
//    @JsonProperty(value = "status_code")
//    val statusCode: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String?,
    val origin: Origin?
) {
    companion object {
        fun fromEntity(entity: RickAndMorth): RickAndMorthDTO {
            return RickAndMorthDTO(
                id = entity.id,
                name = entity.name,
                status = entity.status,
                species = entity.species,
                type = entity.type,
                gender = entity.gender,
                origin = null
            )
        }
    }
    fun toEntity() = RickAndMorth(
        id = null,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender
    )
}
