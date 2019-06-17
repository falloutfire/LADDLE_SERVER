package com.manny.Laddle.Entities

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "Shop")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "employees_number")
    val employeesNumber: Int,
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var laddles: List<Laddle>? = null,
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var users: List<User>? = null
) : Serializable {

    fun toShopDto(): ShopDto {
        return ShopDto(this.id, this.name, this.employeesNumber)
    }
}

class ShopDto(
    val id: Long,
    val name: String,
    val employeesNumber: Int
)

class ShopId(
    val id: Long
)