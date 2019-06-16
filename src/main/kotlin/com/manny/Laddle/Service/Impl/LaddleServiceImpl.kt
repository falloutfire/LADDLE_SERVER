package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Entities.LaddleDto
import com.manny.Laddle.Repository.*
import com.manny.Laddle.Service.LaddleService
import org.springframework.stereotype.Service
import java.util.*

@Service
class LaddleServiceImpl(
    private val laddleRepository: LaddleRepository,
    private val zoneRepository: ZoneRepository,
    private val pointRepository: PointRepository,
    private val propertyRepository: PropertyRepository,
    private val refractoryRepository: RefractoryRepository,
    private val shopRepository: ShopRepository

) : LaddleService {
    override fun delete(id: Long) {
        laddleRepository.deleteById(id)
    }

    override fun save(laddle: LaddleDto) {

        val ladle = laddleRepository.saveAndFlush(Laddle(laddle.id, laddle.name, laddle.photo, laddle.shop))
        for (i in laddle.zones!!) {
            i.laddle = ladle
            val zone = zoneRepository.saveAndFlush(i)
            for (a in zone.points!!) {
                a.zone = zone
                pointRepository.saveAndFlush(a)
            }
            for (a in zone.refractories!!) {
                a.zone = zone
                val ref = refractoryRepository.saveAndFlush(a)
                for (y in ref.properties!!) {
                    y.refractory = ref
                    propertyRepository.saveAndFlush(y)
                }
            }
        }
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