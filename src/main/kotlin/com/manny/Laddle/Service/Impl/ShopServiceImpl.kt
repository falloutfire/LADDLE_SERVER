package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Shop
import com.manny.Laddle.Entities.ShopDto
import com.manny.Laddle.Repository.ShopRepository
import com.manny.Laddle.Service.ShopService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ShopServiceImpl(private val shopRepository: ShopRepository) : ShopService {
    override fun delete(id: Long) {
        shopRepository.deleteById(id)
    }

    override fun save(shop: ShopDto) {
        shopRepository.findShopByNameAndId(shop.name, shop.id).let {
            if (!it.isPresent) {
                shopRepository.save(Shop(shop.id, shop.name))
            }
        }
    }

    override fun add(shop: ShopDto) {
        shopRepository.findShopByName(shop.name).let {
            if (!it.isPresent || (shop.id != it.get().id)) {
                shopRepository.save(Shop(shop.id, shop.name))
            }
        }
    }

    override fun all(): List<ShopDto> {
        return shopRepository.findAll().map { ShopDto(it.id, it.name) }
    }

    override fun getById(id: Long): Optional<Shop> {
        return shopRepository.findById(id)
    }

    override fun find(shop: ShopDto): Optional<Shop> {
        return shopRepository.findShopByName(shop.name)
    }
}