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
    var id: Long,
    @Column(name = "name")
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    var zone: Zone?,
    @OneToMany(mappedBy = "refractory", cascade = [CascadeType.REMOVE])
    var properties: List<Property>? = null
)

class RefractoryDto(
    val id: Long,
    val name: String,
    var zone: Zone?,
    var properties: List<Property>? = null
)