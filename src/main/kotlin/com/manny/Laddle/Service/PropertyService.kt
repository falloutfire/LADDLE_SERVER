package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Property
import java.util.*

interface PropertyService {
    fun delete(id: Long)
    fun save(property: Property)
    fun all(): List<Property>
    fun getById(id: Long): Optional<Property>
    fun find(property: Property): Optional<Property>
    fun add(property: Property)
}