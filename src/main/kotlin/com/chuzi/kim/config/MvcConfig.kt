package com.chuzi.kim.config

import com.chuzi.kim.mvc.resolver.TokenArgumentResolver
import com.chuzi.kim.mvc.resolver.UidArgumentResolver
import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig : WebMvcConfigurer {

    @Resource
    private lateinit var tokenInterceptor: HandlerInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenInterceptor)
            .addPathPatterns("/webrtc/**")
    }

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(UidArgumentResolver())
        argumentResolvers.add(TokenArgumentResolver())
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val configuration = CorsConfiguration()
        configuration.addAllowedOriginPattern("*")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        configuration.allowCredentials = true
        source.registerCorsConfiguration("/**", configuration)
        return CorsFilter(source)
    }
}