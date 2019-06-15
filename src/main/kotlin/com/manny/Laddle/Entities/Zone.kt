package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "Zone")
class Zone(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "laddle_id", nullable = false)
    var laddle: Laddle?,
    @OneToMany(mappedBy = "zone")
    var listPoint: List<Point>,
    @OneToMany(mappedBy = "zone")
    var listRefractory: List<Refractory>
)