package com.manny.Laddle.Service

import org.springframework.security.core.Authentication

interface AuthenticationFacadeService {

    fun getAuthentication(): Authentication
}