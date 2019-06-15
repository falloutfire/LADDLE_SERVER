package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {
    fun findRoleByRoleName(roleName: String): Optional<Role>
    fun findRoleById(id: Long): Optional<Role>
}