package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Point
import com.manny.Laddle.Repository.PointRepository
import com.manny.Laddle.Service.PointService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PointServiceImpl(private val pointRepository: PointRepository) : PointService {

    override fun delete(id: Long) {
        pointRepository.deleteById(id)
    }

    override fun save(point: Point) {
        pointRepository.findPointByXAndYAndZoneAndId(point.x, point.y, point.zone!!, point.id).let {
            if (!it.isPresent) {
                pointRepository.save(point)
            }
        }
    }

    override fun all(): List<Point> {
        return pointRepository.findAll()
    }

    override fun getById(id: Long): Optional<Point> {
        return pointRepository.findById(id)
    }

    override fun find(point: Point): Optional<Point> {
        return pointRepository.findPointByXAndYAndZone(point.x, point.y, point.zone!!)
    }

    override fun add(point: Point) {
        pointRepository.findPointByXAndYAndZone(point.x, point.y, point.zone!!).let {
            if (!it.isPresent || (point.id != it.get().id)) {
                pointRepository.save(point)
            }
        }
    }
}