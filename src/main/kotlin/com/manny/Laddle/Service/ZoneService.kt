package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Zone
import com.manny.Laddle.Entities.ZoneDto
import java.util.*

interface ZoneService {
    fun delete(id: Long)
    fun save(zone: ZoneDto)
    fun all(): List<ZoneDto>
    fun getById(id: Long): Optional<Zone>
    fun find(zone: ZoneDto): Optional<Zone>
    fun add(zone: ZoneDto)
}