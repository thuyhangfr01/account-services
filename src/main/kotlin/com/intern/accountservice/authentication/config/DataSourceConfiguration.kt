package com.intern.accountservice.authentication.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration {
    val hostName = System.getenv("HOST_NAME")!!
    val port = System.getenv("DB_PORT")!!
    val userName = System.getenv("USERNAME")!!
    val password = System.getenv("PASSWORD")!!
    val databaseName = System.getenv("DATABASE_NAME")!!

    @Bean
    fun getDataSource(): DataSource? {
        val url = "jdbc:postgresql://$hostName:$port/$databaseName"
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.postgresql.Driver")
        dataSourceBuilder.url(url)
        dataSourceBuilder.username(userName)
        dataSourceBuilder.password(password)
        return dataSourceBuilder.build()
    }
}