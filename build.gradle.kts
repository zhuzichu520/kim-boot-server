import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

App.init(project)
Log.i("应用程序名称：${name}")
var outJarDir = "${rootDir}${File.separator}dist"
Log.i("Jar包输出目录：${outJarDir}")
val appVersion = App.version()
Log.i("应用程序版本号：${appVersion}")

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "com.chuzi"
version = appVersion

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("org.apache.commons:commons-pool2:2.12.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.eatthepath:pushy:0.15.2")
    implementation("commons-io:commons-io:2.14.0")
    implementation("io.netty:netty-all:4.1.100.Final")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("cn.hutool:hutool-all:5.8.22")
    implementation(project(path = ":core"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    archiveFileName.set("kim-boot-server.jar")
    copy {
        from("src/main/resources")
        into("$outJarDir/resources")
    }
    destinationDirectory.set(File(outJarDir))
}