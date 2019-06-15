package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Property
import com.manny.Laddle.Repository.PropertyRepository
import com.manny.Laddle.Service.PropertyService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PropertyServiceImpl(private val propertyRepository: PropertyRepository) : PropertyService {
    override fun delete(id: Long) {
        propertyRepository.deleteById(id)
    }

    override fun save(property: Property) {
        propertyRepository.findPropertyByNameAndId(property.name, property.id).let {
            if (!it.isPresent) {
                propertyRepository.save(property)
            }
        }
    }

    override fun all(): List<Property> {
        return propertyRepository.findAll()
    }

    override fun getById(id: Long): Optional<Property> {
        return propertyRepository.findById(id)
    }

    override fun find(property: Property): Optional<Property> {
        return propertyRepository.findPropertyByName(property.name)
    }

    override fun add(property: Property) {
        propertyRepository.findPropertyByName(property.name).let {
            if (!it.isPresent || (property.id != it.get().id)) {
                propertyRepository.save(property)
            }
        }
    }
}