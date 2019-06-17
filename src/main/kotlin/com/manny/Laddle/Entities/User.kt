package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "username")
    var userName: String? = null,

    @Column(name = "password")
    var userPassword: String? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "middle_name")
    var middleName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    /**
     * Roles are being eagerly loaded here because
     * they are a fairly small collection of items for this example.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: List<Role>? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonIgnore
    var shop: Shop?
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return ArrayList<GrantedAuthority>().also {
            it.addAll(this.roles!!.map { role -> SimpleGrantedAuthority(role.roleName) })
        }
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.userName!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.userPassword!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}

class UserDto(
    var id: Long? = null,
    var login: String? = null,
    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var roles: List<Role>? = null,
    var password: String? = null,
    var shop: ShopDto? = null
) {

    fun toUser(): User {
        return User(
            id,
            login,
            password,
            firstName,
            middleName,
            lastName,
            roles,
            if (shop != null) Shop(shop!!.id, shop!!.name, shop!!.employeesNumber) else null
        )
    }

    companion object {
        fun toUserDto(user: User): UserDto {
            return UserDto(
                user.id,
                user.username,
                user.firstName,
                user.middleName,
                user.lastName,
                user.roles,
                user.password,
                if (user.shop != null) user.shop!!.toShopDto() else null
            )
        }
    }
}