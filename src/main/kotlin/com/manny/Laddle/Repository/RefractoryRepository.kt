package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Refractory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RefractoryRepository : JpaRepository<Refractory, Long> {
    fun findRefractoryByName(name: String): Optional<Refractory>
    fun findRefractoryByNameAndId(name: String, id: Long): Optional<Refractory>
}