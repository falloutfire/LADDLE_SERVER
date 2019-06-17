package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Point
import com.manny.Laddle.Entities.PointDto
import com.manny.Laddle.Repository.PointRepository
import com.manny.Laddle.Service.PointService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PointServiceImpl(private val pointRepository: PointRepository) : PointService {

    override fun delete(id: Long) {
        pointRepository.deleteById(id)
    }

    override fun save(point: PointDto) {
        pointRepository.save(Point(point.id, point.x, point.y, point.zone))
        /*pointRepository.findPointByXAndYAndZoneAndId(point.x, point.y, point.zone, point.id).let {
            if (!it.isPresent) {

            }
        }*/
    }

    override fun all(): List<PointDto> {
        return pointRepository.findAll().map { PointDto(it.id, it.x, it.y, it.zone) }
    }

    override fun getById(id: Long): Optional<Point> {
        return pointRepository.findById(id)
    }

    override fun find(point: PointDto): Optional<Point> {
        return pointRepository.findPointByXAndYAndZone(point.x, point.y, point.zone)
    }

    override fun add(point: PointDto) {
        pointRepository.save(Point(point.id, point.x, point.y, point.zone))
        /*pointRepository.findPointByXAndYAndZone(point.x, point.y, point.zone).let {
            if (!it.isPresent || (point.id != it.get().id)) {

            }
        }*/
    }
}