package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Laddle
import java.util.*

interface LaddleService {
    fun delete(id: Long)
    fun save(laddle: Laddle)
    fun all(): List<Laddle>
    fun getById(id: Long): Optional<Laddle>
    fun find(laddle: Laddle): Optional<Laddle>
    fun add(laddle: Laddle)
}