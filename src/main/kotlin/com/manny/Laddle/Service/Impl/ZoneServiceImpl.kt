package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Zone
import com.manny.Laddle.Entities.ZoneDto
import com.manny.Laddle.Repository.ZoneRepository
import com.manny.Laddle.Service.ZoneService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ZoneServiceImpl(private val zoneRepository: ZoneRepository) : ZoneService {

    override fun save(zone: ZoneDto) {
        zoneRepository.findZoneByNameAndId(zone.name, zone.id).let {
            if (it.isPresent) {
                zoneRepository.save(Zone(zone.id, zone.name, zone.laddle))
            }
        }
    }

    override fun delete(id: Long) {
        zoneRepository.deleteById(id)
    }

    override fun add(zone: ZoneDto) {
        zoneRepository.findZoneByName(zone.name).let {
            if (!it.isPresent || (zone.id != it.get().id)) {
                zoneRepository.save(Zone(zone.id, zone.name, zone.laddle))
            }
        }
    }

    override fun all(): List<ZoneDto> {
        return zoneRepository.findAll().map { ZoneDto(it.id, it.name, it.laddle, it.points, it.refractories) }
    }

    override fun getById(id: Long): Optional<Zone> {
        return zoneRepository.findById(id)
    }

    override fun find(zone: ZoneDto): Optional<Zone> {
        return zoneRepository.findZoneByName(zone.name)
    }
}