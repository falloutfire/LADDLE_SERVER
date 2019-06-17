package com.manny.Laddle.Security

import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.transaction.annotation.Transactional
import javax.naming.AuthenticationException
import org.springframework.security.oauth2.provider.AuthorizationRequest
import org.springframework.security.oauth2.provider.TokenRequest


open class CustomTokenService : DefaultTokenServices() {

    @Transactional
    @Synchronized
    @Throws(AuthenticationException::class)
    override fun createAccessToken(authentication: OAuth2Authentication): OAuth2AccessToken? {

        try {
            return super.createAccessToken(authentication)
        } catch (ex: DuplicateKeyException) {
            LOGGER.info(ex.message)
            return super.getAccessToken(authentication)
        } catch (ex: Exception) {
            LOGGER.info(ex.message)
        }

        return null
    }

    @Transactional
    @Synchronized
    override fun refreshAccessToken(refreshTokenValue: String?, tokenRequest: TokenRequest?): OAuth2AccessToken {
        return super.refreshAccessToken(refreshTokenValue, tokenRequest)
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(CustomTokenService::class.java)
    }
}