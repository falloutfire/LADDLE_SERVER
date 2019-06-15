package com.manny.Laddle.Service.Impl

import com.manny.Laddle.Service.AuthenticationFacadeService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationFacadeServiceImpl : AuthenticationFacadeService {

    override fun getAuthentication(): Authentication = SecurityContextHolder.getContext().authentication
}