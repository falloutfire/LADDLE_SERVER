package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Shop
import com.manny.Laddle.Entities.ShopDto
import java.util.*

interface ShopService {
    fun delete(id: Long)
    fun save(shop: ShopDto)
    fun all(): List<ShopDto>
    fun getById(id: Long): Optional<Shop>
    fun find(shop: ShopDto): Optional<Shop>
    fun add(shop: ShopDto)
}