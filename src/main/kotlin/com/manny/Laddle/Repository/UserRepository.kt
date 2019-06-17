package com.manny.Laddle.Repository

import com.manny.Laddle.Entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserName(username: String): Optional<User>
}