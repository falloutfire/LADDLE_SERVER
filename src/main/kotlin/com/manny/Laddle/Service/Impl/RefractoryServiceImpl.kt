package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Refractory
import com.manny.Laddle.Entities.RefractoryDto
import com.manny.Laddle.Repository.RefractoryRepository
import com.manny.Laddle.Service.RefractoryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RefractoryServiceImpl(private val refractoryRepository: RefractoryRepository) : RefractoryService {
    override fun delete(id: Long) {
        refractoryRepository.deleteById(id)
    }

    override fun save(refractory: RefractoryDto) {
        refractoryRepository.findRefractoryByNameAndId(refractory.name, refractory.id).let {
            if (!it.isPresent && refractory.id == it.get().id) {
                refractoryRepository.save(Refractory(refractory.id, refractory.name, refractory.zone))
            }
        }
    }

    override fun all(): List<RefractoryDto> {
        return refractoryRepository.findAll().map { RefractoryDto(it.id, it.name, it.zone, it.properties) }
    }

    override fun getById(id: Long): Optional<Refractory> {
        return refractoryRepository.findById(id)
    }

    override fun find(refractory: RefractoryDto): Optional<Refractory> {
        return refractoryRepository.findRefractoryByName(refractory.name)
    }

    override fun add(refractory: RefractoryDto) {
        refractoryRepository.findRefractoryByName(refractory.name).let {
            if (!it.isPresent || (refractory.id != it.get().id)) {
                refractoryRepository.save(Refractory(refractory.id, refractory.name, refractory.zone))
            }
        }
    }
}