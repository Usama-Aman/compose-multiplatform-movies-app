package org.sam.app.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.sam.app.common.Constants
import org.sam.app.data.repository.MoviesRepositoryImpl
import org.sam.app.domain.repository.MoviesRepository

private const val TIME_OUT = 300_000L

@OptIn(ExperimentalSerializationApi::class)
val ktorModule = module {
    single {
        HttpClient {

            install(DefaultRequest) {
                accept(ContentType.Application.Json)
                contentType(ContentType.Application.Json)
                header(
                    HttpHeaders.Authorization,
                    "Bearer ${Constants.TOKEN}"
                )
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HttpClient - $message")
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                    encodeDefaults = true
                })
            }

            install(HttpTimeout) {
                connectTimeoutMillis = TIME_OUT
                requestTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
            }
        }
    }
}

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(httpClient = get())
    }
}