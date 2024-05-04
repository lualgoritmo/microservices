package com.luciano.microservices.service//package com.luciano.microservices.service
//
//import com.luciano.microservices.controller.dto.RickMorthResponse
//import com.luciano.microservices.model.RickAndMorth
//import com.luciano.microservices.repository.RickAndMorthRepository
//import kotlinx.coroutines.reactive.awaitFirstOrNull
//import org.slf4j.LoggerFactory
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//import org.springframework.web.reactive.function.client.WebClient
//import java.util.*
//
//@Service
//class RickAndMorthServiceImpl(
//    private val webClient: WebClient,
//    private val rickAndMorthRepository: RickAndMorthRepository
//) {
//    private val logger = LoggerFactory.getLogger(javaClass)
//
//    @Transactional
//    suspend fun fetchAndSaveCharacters() {
//        try {
//            val response = webClient.get()
//                .uri("https://rickandmortyapi.com/api/character")
//                .retrieve()
//                .bodyToMono(RickMorthResponse::class.java)
//                .awaitFirstOrNull()
//
//            response?.let { characters ->
//                val rickAndMorth = characters.results.map { character ->
//                    character.toEntity()
//                }
//                logger.info("Salvando personagem ${response.results}")
//                rickAndMorthRepository.saveAll(rickAndMorth)
//            }
//        } catch (ex: Exception) {
//            logger.error("Erro ao buscar e salvar personagens do Rick and Morty API", ex)
//            throw ex
//        }
//    }
//
//    suspend fun getAllPersons(): List<RickAndMorth> {
//        logger.info("Personagens aqui")
//        return rickAndMorthRepository.findAll()
//    }
//
//    suspend fun finByIdPerson(idPerson: Long): Optional<RickAndMorth> {
//        try {
//            return rickAndMorthRepository.findById(idPerson)
//        } catch (ex:Exception) {
//            logger.error("Erro ao buscar e salvar personagens do Rick and Morty API", ex)
//            throw ex
//        }
//    }
//}
