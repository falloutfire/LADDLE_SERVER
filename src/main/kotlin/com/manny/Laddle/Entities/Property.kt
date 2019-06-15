package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    var refractory: Refractory?
)