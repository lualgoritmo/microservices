package com.luciano.microservices.controller

import com.luciano.microservices.controller.dto.RickAndMorthDTO
import com.luciano.microservices.service.feingclient.CharacterFeingClientService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class SavePersonFeingController(
    private val characterService: CharacterFeingClientService
) {
    @PostMapping("/fetchAndSave")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCharacter(@RequestParam("page", required = false)page:Int?) =
        characterService.savePersonCharacter(page)
    @GetMapping("/{idPerson}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdPerson(@PathVariable idPerson: Long): RickAndMorthDTO {
        val person = characterService.getByIdPerson(idPerson)
        return RickAndMorthDTO.fromEntity(person)

    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCharacters(): List<RickAndMorthDTO> {
        return characterService.getCharacterAll().map {
            RickAndMorthDTO.fromEntity(it)
        }
    }
}
