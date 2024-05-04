package com.luciano.microservices.controller//package com.luciano.microservices.controller
//
//import com.luciano.microservices.controller.dto.RickAndMorthDTO
//import com.luciano.microservices.model.RickAndMorth
//import com.luciano.microservices.service.RickAndMorthServiceImpl
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.ResponseStatus
//import org.springframework.web.bind.annotation.RestController
//import java.util.*
//
//@RestController
//@RequestMapping("/person")
//class RickAndMorthController {
//    @Autowired
//    lateinit var service: RickAndMorthServiceImpl
//
//    @GetMapping("/fetchAndSave")
//    @ResponseStatus(HttpStatus.OK)
//    suspend fun fetchAndSaveCharacters() {
//        service.fetchAndSaveCharacters()
//    }
//    @GetMapping("/{idPerson}")
//    @ResponseStatus(HttpStatus.OK)
//    suspend fun findByIdPerson(@PathVariable idPerson: Long): Optional<RickAndMorth> {
//        return service.finByIdPerson(idPerson)
//    }
//    @GetMapping("/characters")
//    @ResponseStatus(HttpStatus.OK)
//    suspend fun getAllperson(): List<RickAndMorthDTO> {
//        val persons = service.getAllPersons()
//        return persons.map { RickAndMorthDTO.fromEntity(it) }
//    }
//
////    @GetMapping("/characters")
////    @ResponseStatus(HttpStatus.OK)
////    suspend fun getAllperson(): List<RickAndMorthDTO> {
////       val person = service.getAllPersons()
////        return person.map {
////            RickAndMorthDTO(
////                name = it.name,
////                status = it.status,
////                species = it.species
////            )
////        }
////    }
//
//}