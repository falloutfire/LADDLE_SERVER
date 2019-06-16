package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Entities.LaddleDto
import com.manny.Laddle.Entities.LaddleListEl
import java.util.*

interface LaddleService {
    fun delete(id: Long)
    fun save(laddle: LaddleDto)
    fun all(): List<LaddleListEl>
    fun getById(id: Long): Optional<Laddle>
    fun add(laddle: LaddleDto)
    fun find(laddle: LaddleDto): Optional<Laddle>
}