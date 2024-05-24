package com.luciano.microservices.service

import com.luciano.microservices.controller.dto.RickAndMorthDTO
import com.luciano.microservices.controller.dto.RickMorthResponse
import com.luciano.microservices.repository.RickAndMorthRepository
import com.luciano.microservices.service.feingclient.CharacterFeingClientService
import com.luciano.microservices.service.feingclient.RickAndMorthFeingClient
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class CharacterFeingClientServiceTest {
    @Mock
    private lateinit var rickAndMorthRepository: RickAndMorthRepository

    @Mock
    private lateinit var feingClient: RickAndMorthFeingClient

    @InjectMocks
    private lateinit var feingClientService: CharacterFeingClientService

//    @BeforeEach
//    fun setUp() {
//        rickAndMorthRepository = mock(RickAndMorthRepository::class.java)
//        feingClientService = CharacterFeingClientService(
//            rickAndMorthRepository = rickAndMorthRepository,
//            feingClient = feingClient
//        )
//    }

    @Test
    fun `When searching for characters you must save them`() {
        val result = listRickAndMorthDTO()
        val list = RickMorthResponse(result)
        val response = listRickAndMorthDTO()

        `when`(feingClient.getCharacterAll(page = 1)).thenReturn(list)

        feingClientService.savePersonCharacter(page = 1)

        verify(rickAndMorthRepository, times(1)).saveAll(anyList())
    }

    @Test
    fun `when there are no characters in the list it doesn't save`() {

        `when`(feingClient.getCharacterAll(any())).thenThrow(RuntimeException::class.java)

        assertThrows<RuntimeException> { feingClientService.savePersonCharacter(any()) }
        verify(rickAndMorthRepository, times(0)).saveAll(anyList())
    }

    @Test
    fun `getbyidperson exception`() {
        `when`(rickAndMorthRepository.findById(any())).thenThrow(RuntimeException::class.java)

        assertThrows<RuntimeException> { feingClientService.getByIdPerson(anyLong()) }
        verify(rickAndMorthRepository, times(1)).findById(anyLong())
    }

    @Test
    fun `when the getCharacterAll method is called it must return a list`() {
        val listRickMarth = RickMorthResponse(listRickAndMorthDTO())
        val response = listRickMarth.results.map { it.toEntity() }
        `when`(rickAndMorthRepository.findAll()).thenReturn(response)

        feingClientService.getCharacterAll()

        verify(rickAndMorthRepository, times(1)).findAll()
    }

    @Test
    fun `when the getCharacterAll method is executed with failure it must return an exception`() {

        `when`(rickAndMorthRepository.findAll()).thenThrow(RuntimeException::class.java)

        assertThrows<RuntimeException> { feingClientService.getCharacterAll() }
        verify(rickAndMorthRepository, times(1)).findAll()
    }

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
