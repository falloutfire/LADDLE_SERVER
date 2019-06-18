package com.manny.Laddle.Controller

import com.manny.Laddle.Entities.PropertyDto
import com.manny.Laddle.Service.AuthenticationFacadeService
import com.manny.Laddle.Service.PropertyService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bd/properties")
class PropertyController(
    private val propertyService: PropertyService,
    private val authenticationFacadeService: AuthenticationFacadeService
) {
    private val log = LoggerFactory.getLogger(LaddleController::class.java)

    @Secured(ResponseValues.ROLE_ADMIN)
    @PostMapping("")
    fun add(@RequestBody property: PropertyDto): ApiResponse {
        log.info(
            String.format(
                "received request to add device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return propertyService.find(property).run {
            if (!isPresent) {
                propertyService.save(property)
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
        return propertyService.getById(id).run {
            if (isPresent) {
                propertyService.delete(id)
                ApiResponse(HttpStatus.OK, "Device ${ResponseValues.DELETED}")
            } else ApiResponse(HttpStatus.NOT_FOUND, "Device ${ResponseValues.NOT_FOUND}")
        }
    }

    @Secured(ResponseValues.ROLE_ADMIN)
    @PutMapping("")
    fun update(@RequestBody property: PropertyDto): ApiResponse {
        log.info(
            String.format(
                "received request to update device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return propertyService.getById(property.id)
            .run {
                if (isPresent) {
                    propertyService.save(property)
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
        return propertyService.all().run {
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
        return propertyService.getById(id).run {
            if (isPresent) ApiResponse(HttpStatus.OK, this) else ApiResponse(
                HttpStatus.NOT_FOUND,
                "Device ${ResponseValues.NOT_FOUND}"
            )
        }
    }
}