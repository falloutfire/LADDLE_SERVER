package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Zone
import com.manny.Laddle.Repository.ZoneRepository
import com.manny.Laddle.Service.ZoneService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ZoneServiceImpl(private val zoneRepository: ZoneRepository) : ZoneService {

    override fun save(zone: Zone) {
        zoneRepository.findZoneByNameAndId(zone.name, zone.id).let {
            if (it.isPresent) {
                zoneRepository.save(zone)
            }
        }
    }

    override fun delete(id: Long) {
        zoneRepository.deleteById(id)
    }

    override fun add(zone: Zone) {
        zoneRepository.findZoneByName(zone.name).let {
            if (!it.isPresent || (zone.id != it.get().id)) {
                zoneRepository.save(zone)
            }
        }
    }

    override fun all(): List<Zone> {
        return zoneRepository.findAll()
    }

    override fun getById(id: Long): Optional<Zone> {
        return zoneRepository.findById(id)
    }

    override fun find(zone: Zone): Optional<Zone> {
        return zoneRepository.findZoneByName(zone.name)
    }
}