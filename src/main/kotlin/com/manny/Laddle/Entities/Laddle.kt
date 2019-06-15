package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "Laddle")
class Laddle(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "photo")
    val photo: ByteArray,
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    var shop: Shop?,
    @OneToMany(mappedBy = "laddle")
    var listZone: List<Zone>
)