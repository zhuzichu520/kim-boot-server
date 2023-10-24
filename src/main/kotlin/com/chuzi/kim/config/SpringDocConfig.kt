package com.chuzi.kim.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfig {

    @Bean
    fun restfulOpenAPI(): OpenAPI {
        return OpenAPI().info(Info().apply {
            title = "KIM Push Service APIs."
            description = "KIM客户端接口文档"
            version = "3.0"
            contact = Contact().apply {
                name = "zhuzichu"
                url = "https://github.com/zhuzichu520"
                email = "zhuzichu520@outlook.com"
            }
            license = License().apply {
                name = "Apache 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }).externalDocs(ExternalDocumentation().apply {
            description = "SpringDoc Wiki Documentation"
            url = "https://springdoc.org/v2"
        })
    }

}

