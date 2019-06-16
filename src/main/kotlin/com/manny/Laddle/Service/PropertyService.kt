package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Property
import com.manny.Laddle.Entities.PropertyDto
import java.util.*

interface PropertyService {
    fun delete(id: Long)
    fun save(property: PropertyDto)
    fun all(): List<PropertyDto>
    fun getById(id: Long): Optional<Property>
    fun find(property: PropertyDto): Optional<Property>
    fun add(property: PropertyDto)
}