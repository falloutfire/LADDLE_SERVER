package com.manny.Laddle.Controller

import com.manny.Laddle.Controller.ResponseValues.Companion.CREATED
import com.manny.Laddle.Controller.ResponseValues.Companion.DELETED
import com.manny.Laddle.Controller.ResponseValues.Companion.EXIST
import com.manny.Laddle.Controller.ResponseValues.Companion.NOT_FOUND
import com.manny.Laddle.Controller.ResponseValues.Companion.ROLE_ADMIN
import com.manny.Laddle.Controller.ResponseValues.Companion.ROLE_USER
import com.manny.Laddle.Controller.ResponseValues.Companion.UPDATED
import com.manny.Laddle.Entities.LaddleDto
import com.manny.Laddle.Entities.User
import com.manny.Laddle.Service.AuthenticationFacadeService
import com.manny.Laddle.Service.LaddleService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.context.SecurityContextHolder



@RestController
@RequestMapping("bd/ladles")
class LaddleController(
    private val laddleService: LaddleService,
    private val authenticationFacadeService: AuthenticationFacadeService
) {

    private val log = LoggerFactory.getLogger(LaddleController::class.java)

    @Secured(ROLE_ADMIN)
    @PostMapping("")
    fun add(@RequestBody laddle: LaddleDto): ApiResponse {
        log.info(
            String.format(
                "received request to add device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        laddle.shop = (SecurityContextHolder.getContext().authentication.principal as User).shop!!
        return laddleService.find(laddle).run {
            if (!isPresent) {
                laddleService.save(laddle)
                ApiResponse(HttpStatus.CREATED, "Laddle $CREATED")
            } else {
                ApiResponse(HttpStatus.OK, "Laddle $EXIST")
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
        return laddleService.getById(id).run {
            if (isPresent) {
                laddleService.delete(id)
                ApiResponse(HttpStatus.OK, "Device $DELETED")
            } else ApiResponse(HttpStatus.NOT_FOUND, "Device $NOT_FOUND")
        }
    }

    @Secured(ROLE_ADMIN)
    @PutMapping("")
    fun update(@RequestBody laddle: LaddleDto): ApiResponse {
        log.info(
            String.format(
                "received request to update device %s",
                authenticationFacadeService.getAuthentication().principal
            )
        )
        return laddleService.getById(laddle.id)
            .run {
                if (isPresent) {
                    laddleService.save(laddle)
                    ApiResponse(HttpStatus.OK, "Device $UPDATED")
                } else {
                    ApiResponse(HttpStatus.NOT_FOUND, "Device $NOT_FOUND")
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
        return laddleService.all().run {
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
        return laddleService.getById(id).run {
            if (isPresent) ApiResponse(HttpStatus.OK, this) else ApiResponse(
                HttpStatus.NOT_FOUND,
                "Device $NOT_FOUND"
            )
        }
    }
}