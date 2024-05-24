package com.luciano.microservices.service.feingclient

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
    fun savePersonCharacter(page: Int?):List<RickAndMorth> {
        val response = feingClient.getCharacterAll(page ?: 1)
        val rickAndMorth = response.results.map { it.toEntity() }
        if (page != null && rickAndMorth != null) {
          return rickAndMorthRepository.saveAll(rickAndMorth)
        } else {
            throw Exception("Os campos estão vazios")
        }

    }

    fun getCharacterAll(): List<RickAndMorth> {
        val listCharacter = rickAndMorthRepository.findAll()
        if (listCharacter.isNotEmpty()) {
            return listCharacter
        } else {
            throw Exception("Essa lista está vazia!")
        }
    }

    fun getByIdPerson(idPerson: Long): RickAndMorth {
        return rickAndMorthRepository.findById(idPerson).orElseThrow {
            RuntimeException("Esse id não existe!")
        }
    }
    //private fun getCharacterAllPerson(page: Int?): RickMorthResponse = feingClient.getCharacterAll(page?:1)

}
