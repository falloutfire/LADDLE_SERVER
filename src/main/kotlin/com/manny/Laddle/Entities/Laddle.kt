package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false/*, cascade = [CascadeType.REMOVE]*/)
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    var shop: Shop,
    @OneToMany(mappedBy = "laddle", cascade = [CascadeType.REMOVE])
    var zones: List<Zone>? = null
)

class LaddleDto(
    val id: Long,
    val name: String,
    val photo: ByteArray,
    var shop: Shop?,
    var zones: List<Zone>? = null
)

class LaddleListEl(
    val id: Long,
    val name: String,
    val photo: ByteArray,
    var shop: ShopId,
    var zones: List<Zone>? = null
)
