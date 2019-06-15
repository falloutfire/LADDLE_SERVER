package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Point
import java.util.*

interface PointService {
    fun delete(id: Long)
    fun save(point: Point)
    fun all(): List<Point>
    fun getById(id: Long): Optional<Point>
    fun find(point: Point): Optional<Point>
    fun add(point: Point)
}