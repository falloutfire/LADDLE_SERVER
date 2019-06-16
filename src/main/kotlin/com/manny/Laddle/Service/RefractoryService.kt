package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Refractory
import com.manny.Laddle.Entities.RefractoryDto
import java.util.*

interface RefractoryService {
    fun delete(id: Long)
    fun save(refractory: RefractoryDto)
    fun all(): List<RefractoryDto>
    fun getById(id: Long): Optional<Refractory>
    fun find(refractory: RefractoryDto): Optional<Refractory>
    fun add(refractory: RefractoryDto)
}