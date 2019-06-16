package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Point
import com.manny.Laddle.Entities.PointDto
import java.util.*

interface PointService {
    fun delete(id: Long)
    fun save(point: PointDto)
    fun all(): List<PointDto>
    fun getById(id: Long): Optional<Point>
    fun find(point: PointDto): Optional<Point>
    fun add(point: PointDto)
}