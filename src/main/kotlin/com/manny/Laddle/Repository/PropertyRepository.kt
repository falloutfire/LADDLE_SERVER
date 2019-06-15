package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Property
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PropertyRepository : JpaRepository<Property, Long> {
    fun findPropertyByNameAndId(name: String, id: Long): Optional<Property>
    fun findPropertyByName(name: String): Optional<Property>
}