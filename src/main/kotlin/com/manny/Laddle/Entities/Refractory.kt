package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "Refractory")
class Refractory(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    @JsonIgnore
    var zone: Zone?,
    @OneToMany(mappedBy = "refractory", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    var properties: List<Property>? = null
)

class RefractoryDto(
    val id: Long,
    val name: String,
    var zone: Zone?,
    var properties: List<Property>? = null
)