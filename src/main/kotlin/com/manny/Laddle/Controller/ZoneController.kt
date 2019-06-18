package com.manny.Laddle.Controller

import com.manny.Laddle.Controller.ResponseValues.Companion.ROLE_ADMIN
import com.manny.Laddle.Controller.ResponseValues.Companion.ROLE_USER
import com.manny.Laddle.Entities.ZoneDto
import com.manny.Laddle.Service.AuthenticationFacadeService
import com.manny.Laddle.Service.ZoneService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bd/zones")
class ZoneController(
    private val zoneService: ZoneService,
    private val authenticationFacadeService: AuthenticationFacadeService
) {
    private val log = LoggerFactory.getLogger(LaddleController::class.java)

    @Secured(ROLE_ADMIN)
    @PostMapping("")
    fun add(@RequestBody zone: ZoneDto): ApiResponse {
        log.info(
            String.format(
                "received request to add device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.find(zone).run {
            if (!isPresent) {
                zoneService.save(zone)
                ApiResponse(HttpStatus.CREATED, "Laddle ${ResponseValues.CREATED}")
            } else {
                ApiResponse(HttpStatus.OK, "Laddle ${ResponseValues.EXIST}")
            }
        }
    }

    @Secured(ROLE_ADMIN)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long): ApiResponse {
        log.info(
            String.format(
                "received request to delete device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.getById(id).run {
            if (isPresent) {
                zoneService.delete(id)
                ApiResponse(HttpStatus.OK, "Device ${ResponseValues.DELETED}")
            } else ApiResponse(HttpStatus.NOT_FOUND, "Device ${ResponseValues.NOT_FOUND}")
        }
    }

    @Secured(ROLE_ADMIN)
    @PutMapping("")
    fun update(@RequestBody zone: ZoneDto): ApiResponse {
        log.info(
            String.format(
                "received request to update device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.getById(zone.id)
            .run {
                if (isPresent) {
                    zoneService.save(zone)
                    ApiResponse(HttpStatus.OK, "Device ${ResponseValues.UPDATED}")
                } else {
                    ApiResponse(HttpStatus.NOT_FOUND, "Device ${ResponseValues.NOT_FOUND}")
                }
            }
    }

    @Secured(ROLE_ADMIN, ROLE_USER)
    @GetMapping("")
    fun all(): ApiResponse {
        log.info(
            String.format(
                "received request to list device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.all().run {
            ApiResponse(HttpStatus.OK, this)
        }
    }

    @Secured(ROLE_ADMIN, ROLE_USER)
    @GetMapping("/{id}")
    fun getById(@PathVariable(value = "id") id: Long): ApiResponse {
        log.info(
            String.format(
                "received request to add device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.getById(id).run {
            if (isPresent) ApiResponse(HttpStatus.OK, this) else ApiResponse(
                HttpStatus.NOT_FOUND,
                "Device ${ResponseValues.NOT_FOUND}"
            )
        }
    }
}