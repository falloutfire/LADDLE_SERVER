package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "role")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(name = "role_name")
    var roleName: String? = null
    @Column(name = "description")
    var description: String? = null

    companion object {
        private val serialVersionUID = 1L
    }
}