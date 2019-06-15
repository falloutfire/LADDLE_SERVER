package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Zone
import java.util.*

interface ZoneService {
    fun delete(id: Long)
    fun save(zone: Zone)
    fun all(): List<Zone>
    fun getById(id: Long): Optional<Zone>
    fun find(zone: Zone): Optional<Zone>
    fun add(zone: Zone)
}