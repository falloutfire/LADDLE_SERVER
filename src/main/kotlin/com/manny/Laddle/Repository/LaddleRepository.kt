package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Entities.Shop
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LaddleRepository : JpaRepository<Laddle, Long> {
    fun findLaddleByNameAndShop(name: String, shop: Shop): Optional<Laddle>
    fun findLaddleByNameAndShopAndId(name: String, shop: Shop, id: Long): Optional<Laddle>
}