package com.luciano.microservices.service.feingclient

import com.luciano.microservices.controller.dto.RickMorthResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

@FeignClient(name = "users-character", url ="\${api_rickMorth_uri}")
interface RickAndMorthFeingClient {
    @GetMapping("/api/character", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getCharacterAll(@RequestParam("page") page: Int? = 1): RickMorthResponse

}