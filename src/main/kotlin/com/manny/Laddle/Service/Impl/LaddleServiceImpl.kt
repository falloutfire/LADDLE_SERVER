package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Entities.LaddleDto
import com.manny.Laddle.Repository.LaddleRepository
import com.manny.Laddle.Service.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class LaddleServiceImpl(
    private val laddleRepository: LaddleRepository,
    private val zoneService: ZoneService,
    private val pointService: PointService,
    private val propertyService: PropertyService,
    private val refractoryService: RefractoryService,
    private val shopService: ShopService

) : LaddleService {
    override fun delete(id: Long) {
        laddleRepository.deleteById(id)
    }

    override fun save(laddle: LaddleDto) {
        laddleRepository.saveAndFlush(Laddle(laddle.id, laddle.name, laddle.photo, laddle.shop, laddle.zones))
    }

    override fun all(): List<LaddleDto> {
        return laddleRepository.findAll().map { LaddleDto(it.id, it.name, it.photo, it.shop, it.zones) }
    }

    override fun getById(id: Long): Optional<Laddle> {
        return laddleRepository.findById(id)
    }

    override fun find(laddle: LaddleDto): Optional<Laddle> {
        return laddleRepository.findLaddleByNameAndShop(laddle.name, laddle.shop)
    }

    override fun add(laddle: LaddleDto) {
        laddleRepository.findLaddleByNameAndShop(laddle.name, laddle.shop).let {
            if (!it.isPresent || (laddle.id != it.get().id)) {
                val laddleIn = Laddle(laddle.id, laddle.name, laddle.photo, laddle.shop, laddle.zones)
                laddleRepository.save(laddleIn)
            }
        }
    }
}