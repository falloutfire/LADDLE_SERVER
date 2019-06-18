package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Zone
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface ZoneRepository : JpaRepository<Zone, Long> {
    fun findZoneByName(name: String): Optional<Zone>
    fun findZoneByNameAndId(name: String, id: Long): Optional<Zone>
}