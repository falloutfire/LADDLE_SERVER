package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Point
import com.manny.Laddle.Entities.Zone
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PointRepository : JpaRepository<Point, Long> {
    fun findPointByXAndYAndZone(x: Int, y: Int, zone: Zone): Optional<Point>
    fun findPointByXAndYAndZoneAndId(x: Int, y: Int, zone: Zone, id: Long): Optional<Point>
}