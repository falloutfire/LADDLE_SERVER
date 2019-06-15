package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "Point")
class Point(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "x")
    val x: Int,
    @Column(name = "y")
    val y: Int,
    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    var zone: Zone?
)