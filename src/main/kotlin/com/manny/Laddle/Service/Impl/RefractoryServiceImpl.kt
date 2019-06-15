package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Refractory
import com.manny.Laddle.Repository.RefractoryRepository
import com.manny.Laddle.Service.RefractoryService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RefractoryServiceImpl(private val refractoryRepository: RefractoryRepository) : RefractoryService {
    override fun delete(id: Long) {
        refractoryRepository.deleteById(id)
    }

    override fun save(refractory: Refractory) {
        refractoryRepository.findRefractoryByNameAndId(refractory.name, refractory.id).let {
            if (!it.isPresent && refractory.id == it.get().id) {
                refractoryRepository.save(refractory)
            }
        }
    }

    override fun all(): List<Refractory> {
        return refractoryRepository.findAll()
    }

    override fun getById(id: Long): Optional<Refractory> {
        return refractoryRepository.findById(id)
    }

    override fun find(refractory: Refractory): Optional<Refractory> {
        return refractoryRepository.findRefractoryByName(refractory.name)
    }

    override fun add(refractory: Refractory) {
        refractoryRepository.findRefractoryByName(refractory.name).let {
            if (!it.isPresent || (refractory.id != it.get().id)) {
                refractoryRepository.save(refractory)
            }
        }
    }
}