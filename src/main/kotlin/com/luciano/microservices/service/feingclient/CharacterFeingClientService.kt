package com.luciano.microservices.service.feingclient

import com.luciano.microservices.controller.dto.RickMorthResponse
import com.luciano.microservices.model.RickAndMorth
import com.luciano.microservices.repository.RickAndMorthRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharacterFeingClientService(
    private val feingClient: RickAndMorthFeingClient,
    private val rickAndMorthRepository: RickAndMorthRepository
) {
    @Transactional
    fun savePersonCharacter(page: Int?) {
        val response = getFeing(page)
        val rickAndMorth = response.results.map { it.toEntity() }
        rickAndMorthRepository.saveAll(rickAndMorth)
    }
    fun getCharacterAll(): List<RickAndMorth> {
        return rickAndMorthRepository.findAll()
    }
    fun getByIdPerson(idPerson: Long): RickAndMorth {
       return rickAndMorthRepository.findById(idPerson).orElseThrow {
           Exception("Esse id não existe!")
        }
    }
    private fun getFeing(page: Int?): RickMorthResponse = feingClient.getCharacterAll(page?:1)

}
