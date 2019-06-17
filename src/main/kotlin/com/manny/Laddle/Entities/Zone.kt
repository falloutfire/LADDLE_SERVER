package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "Zone")
class Zone(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "laddle_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    var laddle: Laddle?,
    @OneToMany(mappedBy = "zone")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var points: List<Point>? = null,
    @OneToMany(mappedBy = "zone")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var refractories: List<Refractory>? = null
)

class ZoneDto(
    val id: Long,
    val name: String,
    var laddle: Laddle?,
    var points: List<Point>? = null,
    var refractories: List<Refractory>? = null
)