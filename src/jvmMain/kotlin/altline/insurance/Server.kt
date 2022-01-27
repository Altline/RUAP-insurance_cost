package altline.insurance

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.HTML

class Server {
    private val log by logger()

    fun start() {
        embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
            install(ContentNegotiation) {
                json()
            }
            install(CORS) {
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                anyHost()
            }
            install(Compression) {
                gzip()
            }

            routing {
                route("/") {
                    get {
                        call.respondHtml(HttpStatusCode.OK, HTML::index)
                    }
                    post {

                    }
                }
                static("/static") {
                    resources()
                }
            }
        }.start(wait = true)
    }
}

fun main() {
    Server().start()
}
