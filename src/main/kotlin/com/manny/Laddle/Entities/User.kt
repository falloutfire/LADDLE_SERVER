package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "username")
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null,

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
    var shop_id: Shop?
)

class UserDto(
    var id: Long? = null,
    var login: String? = null,
    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var roles: List<Role>? = null,
    var shopId: Shop? = null
) {

    companion object {
        fun toUserDto(user: User): UserDto {
            return UserDto(
                user.id,
                user.username,
                user.firstName,
                user.middleName,
                user.lastName,
                user.roles,
                user.shop_id
            )
        }
    }
}