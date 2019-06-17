package com.manny.Laddle.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.security.core.context.SecurityContextHolder
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shop_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    var shop: Shop,
    @OneToMany(mappedBy = "laddle")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
