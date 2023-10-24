package com.chuzi.kim.component.handler.annotation

import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class KIMHandler(val key: String)

