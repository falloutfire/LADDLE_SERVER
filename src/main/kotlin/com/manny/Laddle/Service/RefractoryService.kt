package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Refractory
import java.util.*

interface RefractoryService {
    fun delete(id: Long)
    fun save(refractory: Refractory)
    fun all(): List<Refractory>
    fun getById(id: Long): Optional<Refractory>
    fun find(refractory: Refractory): Optional<Refractory>
    fun add(refractory: Refractory)
}