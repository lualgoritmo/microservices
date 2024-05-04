package com.luciano.microservices.repository

import com.luciano.microservices.model.RickAndMorth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RickAndMorthRepository:JpaRepository<RickAndMorth, Long>