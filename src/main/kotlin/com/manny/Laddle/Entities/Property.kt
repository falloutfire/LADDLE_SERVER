package com.manny.Laddle.Entities

import javax.persistence.*

@Entity
@Table(name = "Property")
class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "value")
    val value: String,
    @Column(name = "type")
    val type: String,
    @ManyToOne
    @JoinColumn(name = "refractory_id", nullable = false)
    var refractory: Refractory?
)