import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import react.dom.render
import view.Base

private class Client {
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    val httpClient = HttpClient() {
        install(JsonFeature) { serializer = KotlinxSerializer() }
    }
    val predictionService = PredictionService(httpClient)

    fun start() {
        window.onload = {
            val root = document.getElementById("root")!!
            render(root) {
                child(Base::class) {
                    attrs.coroutineScope = coroutineScope
                    attrs.predictionService = predictionService
                }
            }
        }
    }
}

fun main() {
    Client().start()
}

