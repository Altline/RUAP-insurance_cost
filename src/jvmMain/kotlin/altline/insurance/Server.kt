package altline.insurance

import Features
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*

class Server(val predictionService: PredictionService) {
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
                        this@Server.log.info("Received prediction request.")
                        val features = call.receive<Features>()
                        val prediction = predictionService.getPrediction(features)
                        if (prediction != null) {
                            call.respond(prediction)
                        } else call.respond(HttpStatusCode.InternalServerError)
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
    Server(PredictionService()).start()
}
