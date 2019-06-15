package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "Refractory")
class Refractory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    var zone: Zone?,
    @OneToMany(mappedBy = "refractory")
    var listProperty: List<Property>
)