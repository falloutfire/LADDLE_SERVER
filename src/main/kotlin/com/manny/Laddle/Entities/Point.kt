package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "Point")
class Point(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    @Column(name = "x")
    var x: Int,
    @Column(name = "y")
    var y: Int,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    var zone: Zone
)

class PointDto(
    val id: Long,
    val x: Int,
    val y: Int,
    var zone: Zone
)