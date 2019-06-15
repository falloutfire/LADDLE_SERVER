package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Shop
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ShopRepository : JpaRepository<Shop, Long> {
    fun findShopByName(name: String): Optional<Shop>
    fun findShopByNameAndId(name: String, id: Long): Optional<Shop>
}