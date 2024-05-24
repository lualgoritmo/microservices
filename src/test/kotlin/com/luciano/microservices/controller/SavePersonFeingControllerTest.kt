package com.luciano.microservices.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.microservices.controller.dto.RickAndMorthDTO
import com.luciano.microservices.model.RickAndMorth
import com.luciano.microservices.repository.RickAndMorthRepository
import com.luciano.microservices.service.feingclient.CharacterFeingClientService
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class SavePersonFeingControllerTest {

    @Mock
    lateinit var characterService: CharacterFeingClientService

    @Mock
    lateinit var repository: RickAndMorthRepository

    @InjectMocks
    lateinit var savePersonFeingController: SavePersonFeingController

    @Autowired
    lateinit var objectMapper: ObjectMapper

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(savePersonFeingController).build()
    }
    @Test
    fun `When calling the saveCharacter method, you must save the characters`() {

        val result = listRickAndMorthDTO().map {
            it.toEntity()
        }

        given(characterService.savePersonCharacter(page = 1)).willReturn(result)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/person/fetchAndSave")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(result)))
            .andExpect(MockMvcResultMatchers.status().isCreated)

    }
    @Test
    fun `when calling the getByIdPerson method, you must get person`() {
        val personId = personId()
        repository.save(personId)

        given(characterService.getByIdPerson(1)).willReturn(personId)

        mockMvc.perform(MockMvcRequestBuilders.get("/person/{idPerson}", personId.id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(personId)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.id").value(personId.id))
            .andExpect(jsonPath("$.status").value(personId.status))
    }

    @Test
    fun `When the getAllCharacters method is called, it should return a list of characters`() {
        val result = listRickAndMorthDTO().map {
            it.toEntity()
        }

        given(characterService.getCharacterAll()).willReturn(result)

        mockMvc.perform(MockMvcRequestBuilders.get("/person/all")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(result)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].name", Matchers.equalTo(result[0].name)))
    }
    private fun personId() = RickAndMorth(
    id = 1,
    name = "Luciano",
    status = "Vivo",
    species = "Lindo!",
    type = null,
    gender = null)
    private fun listRickAndMorthDTO() = listOf(
        RickAndMorthDTO(
            id = 1,
            name = "Luciano",
            status = "Vivo",
            species = "Lindo!",
            type = null,
            gender = null,
            origin = null
        ),
        RickAndMorthDTO(
            id = 2,
            name = "Cleo",
            status = "Viva",
            species = "Linda!",
            type = null,
            gender = null,
            origin = null
        )
    )
}
