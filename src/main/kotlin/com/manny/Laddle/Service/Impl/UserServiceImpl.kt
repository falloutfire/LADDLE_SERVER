package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Entities.Role
import com.manny.Laddle.Entities.User
import com.manny.Laddle.Entities.UserDto
import com.manny.Laddle.Repository.RoleRepository
import com.manny.Laddle.Repository.UserRepository
import com.manny.Laddle.Service.RoleService
import com.manny.Laddle.Service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service("userService")
class UserDetailsService : UserDetailsService, UserService {

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val roleRepository: RoleRepository? = null

    @Autowired
    private val passwordEncoder: BCryptPasswordEncoder? = null

    override fun saveUser(user: User) {
        val userWithDuplicateUsername = userRepository!!.findByUserName(user.username!!)
        if (userWithDuplicateUsername.isPresent && user.id != userWithDuplicateUsername.get().id) {
            log.error(String.format("Duplicate username ", user.username))
            throw RuntimeException("Duplicate username.")
        }

        user.userPassword = passwordEncoder!!.encode(user.password!!)
        val roleTypes = ArrayList<Role>()
        user.roles!!.stream().map { role ->
            roleRepository?.findRoleById(role.id!!).let {
                if (it!!.isPresent) {
                    roleTypes.add(role)
                }
            }
        }
        userRepository.save(user)
    }

    override fun deleteUser(id: Long) {
        userRepository?.deleteById(id)
    }

    override fun findOneUser(id: Long): UserDto {
        return UserDto.toUserDto(userRepository!!.findById(id).get())
    }

    override fun findAllUser(): List<UserDto> {
        return userRepository!!.findAll().map {
            UserDto.toUserDto(it)
        }
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        val user = userRepository?.findByUserName(s)
            ?: throw UsernameNotFoundException(String.format("The username %s doesn't exist", s))
        return user.get()
    }

    companion object {

        private val log = LoggerFactory.getLogger(UserDetailsService::class.java)
    }
}


@Component
@Service
class RoleServiceImpl : RoleService {
    @Autowired
    private val roleRepository: RoleRepository? = null

    override fun findAllRole(): List<Role> {
        return roleRepository?.findAll() as List<Role>
    }

}