package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Property
import com.manny.Laddle.Entities.PropertyDto
import com.manny.Laddle.Repository.PropertyRepository
import com.manny.Laddle.Service.PropertyService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PropertyServiceImpl(private val propertyRepository: PropertyRepository) : PropertyService {
    override fun delete(id: Long) {
        propertyRepository.deleteById(id)
    }

    override fun save(property: PropertyDto) {
        propertyRepository.save(
            Property(
                property.id,
                property.name,
                property.value,
                property.type,
                property.characteristic,
                property.refractory
            )
        )
        /*propertyRepository.findPropertyByNameAndId(property.name, property.id).let {
            if (!it.isPresent) {
                propertyRepository.save(
                    Property(
                        property.id,
                        property.name,
                        property.value,
                        property.type,
                        property.refractory
                    )
                )
            }
        }*/
    }

    override fun all(): List<PropertyDto> {
        return propertyRepository.findAll()
            .map { PropertyDto(it.id, it.name, it.value, it.type, it.characteristic, it.refractory) }
    }

    override fun getById(id: Long): Optional<Property> {
        return propertyRepository.findById(id)
    }

    override fun find(property: PropertyDto): Optional<Property> {
        return propertyRepository.findPropertyByName(property.name)
    }

    override fun add(property: PropertyDto) {
        propertyRepository.save(
            Property(
                property.id,
                property.name,
                property.value,
                property.type,
                property.characteristic,
                property.refractory
            )
        )
        /*propertyRepository.findPropertyByName(property.name).let {
            if (!it.isPresent || (property.id != it.get().id)) {

            }
        }*/
    }
}