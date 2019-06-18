package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Entities.Shop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface LaddleRepository : JpaRepository<Laddle, Long> {
    fun findLaddleByNameAndShop(name: String, shop: Shop): Optional<Laddle>
    fun findLaddleByNameAndShopAndId(name: String, shop: Shop, id: Long): Optional<Laddle>
}