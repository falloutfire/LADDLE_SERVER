package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Repository.LaddleRepository
import com.manny.Laddle.Service.LaddleService
import org.springframework.stereotype.Service
import java.util.*

@Service
class LaddleServiceImpl(private val laddleRepository: LaddleRepository) : LaddleService {
    override fun delete(id: Long) {
        laddleRepository.deleteById(id)
    }

    override fun save(laddle: Laddle) {
        laddleRepository.findLaddleByNameAndShopAndId(laddle.name, laddle.shop!!, laddle.id).let {
            if (!it.isPresent) {
                laddleRepository.save(laddle)
            }
        }
    }

    override fun all(): List<Laddle> {
        return laddleRepository.findAll()
    }

    override fun getById(id: Long): Optional<Laddle> {
        return laddleRepository.findById(id)
    }

    override fun find(laddle: Laddle): Optional<Laddle> {
        return laddleRepository.findLaddleByNameAndShop(laddle.name, laddle.shop!!)
    }

    override fun add(laddle: Laddle) {
        laddleRepository.findLaddleByNameAndShop(laddle.name, laddle.shop!!).let {
            if (!it.isPresent || (laddle.id != it.get().id)) {
                laddleRepository.save(laddle)
            }
        }
    }
}