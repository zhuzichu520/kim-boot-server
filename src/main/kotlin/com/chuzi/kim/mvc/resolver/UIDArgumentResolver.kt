package com.chuzi.kim.mvc.resolver

import com.chuzi.kim.annotation.UID
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


class UIDArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UID::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter, mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?
    ): Any? {
        return webRequest.getAttribute(UID::class.java.getName(), RequestAttributes.SCOPE_REQUEST)
    }

}