package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Shop
import java.util.*

interface ShopService {
    fun delete(id: Long)
    fun save(shop: Shop)
    fun all(): List<Shop>
    fun getById(id: Long): Optional<Shop>
    fun find(shop: Shop): Optional<Shop>
    fun add(shop: Shop)
}