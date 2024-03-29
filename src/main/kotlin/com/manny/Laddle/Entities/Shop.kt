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
    var id: Long,
    @Column(name = "name")
    var name: String,
    @Column(name = "employees_number")
    var employeesNumber: Int,
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var laddles: List<Laddle>? = null,
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var users: List<User>? = null
) : Serializable {

    fun toShopDto(): ShopDto {
        return ShopDto(this.id, this.name, this.employeesNumber)
    }

    @PreRemove
    fun preRemove() {
        users?.forEach { it.shop = null }
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