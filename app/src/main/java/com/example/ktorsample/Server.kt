package com.example.ktorsample

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class Server(
    private val sslCredentials: SslCredentials
) {

    private val environment = applicationEngineEnvironment {
        connector {
            port = HTTP_PORT
        }
        sslConnector(
            sslCredentials.keyStore,
            sslCredentials.keyAlias,
            { sslCredentials.keyPassword.toCharArray() },
            { sslCredentials.keyPassword.toCharArray() }

        ) {
            port = HTTPS_PORT
        }
        module {
            routing {
                get("/example") {
                    call.respondText("Success!")
                }
            }
        }
    }

    private val server = embeddedServer(Netty, environment)

    fun start() {
        server.start()
    }

    private companion object {
        const val HTTPS_PORT = 8090
        const val HTTP_PORT = 8091
    }
}