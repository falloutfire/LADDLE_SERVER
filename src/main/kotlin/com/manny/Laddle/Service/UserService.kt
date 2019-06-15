package com.manny.Laddle.Service

import com.manny.Laddle.Entities.Role
import com.manny.Laddle.Entities.User
import com.manny.Laddle.Entities.UserDto

interface UserService {
    fun findOneUser(id: Long): UserDto
    fun findAllUser(): List<UserDto>
    fun deleteUser(id: Long)
    fun saveUser(user: User)
}

interface RoleService {
    fun findAllRole(): List<Role>
}