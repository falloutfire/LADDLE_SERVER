package com.manny.Laddle.Controller

import com.manny.Laddle.Entities.Laddle
import com.manny.Laddle.Service.AuthenticationFacadeService
import com.manny.Laddle.Service.LaddleService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bd/zone")
class ZoneController(
    private val zoneService: LaddleService,
    private val authenticationFacadeService: AuthenticationFacadeService
) {
    private val log = LoggerFactory.getLogger(LaddleController::class.java)

    @Secured(ResponseValues.ROLE_ADMIN)
    @PostMapping("")
    fun add(@RequestBody laddle: Laddle): ApiResponse {
        log.info(
            String.format(
                "received request to add device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.find(laddle).run {
            if (!isPresent) {
                zoneService.save(laddle)
                ApiResponse(HttpStatus.CREATED, "Laddle ${ResponseValues.CREATED}")
            } else {
                ApiResponse(HttpStatus.OK, "Laddle ${ResponseValues.EXIST}")
            }
        }
    }

    @Secured(ResponseValues.ROLE_ADMIN)
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

    @Secured(ResponseValues.ROLE_ADMIN)
    @PutMapping("")
    fun update(@RequestBody laddle: Laddle): ApiResponse {
        log.info(
            String.format(
                "received request to update device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return zoneService.getById(laddle.id)
            .run {
                if (isPresent) {
                    zoneService.save(laddle)
                    ApiResponse(HttpStatus.OK, "Device ${ResponseValues.UPDATED}")
                } else {
                    ApiResponse(HttpStatus.NOT_FOUND, "Device ${ResponseValues.NOT_FOUND}")
                }
            }
    }

    @Secured(ResponseValues.ROLE_ADMIN, ResponseValues.ROLE_USER)
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

    @Secured(ResponseValues.ROLE_ADMIN, ResponseValues.ROLE_USER)
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