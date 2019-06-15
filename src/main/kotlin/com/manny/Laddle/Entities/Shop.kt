package com.manny.Laddle.Entities

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "Shop")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    var listLaddle: List<Laddle>,
    @OneToMany(mappedBy = "shopId", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var listUser: List<User>
)