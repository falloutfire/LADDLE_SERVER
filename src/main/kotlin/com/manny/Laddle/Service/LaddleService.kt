package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Entities.LaddleDto
import java.util.*

interface LaddleService {
    fun delete(id: Long)
    fun save(laddle: LaddleDto)
    fun all(): List<LaddleDto>
    fun getById(id: Long): Optional<Laddle>
    fun add(laddle: LaddleDto)
    fun find(laddle: LaddleDto): Optional<Laddle>
}