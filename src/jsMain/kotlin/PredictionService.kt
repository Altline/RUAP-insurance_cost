import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.browser.window

class PredictionService(val httpClient: HttpClient) {

    private val endpoint = window.location.origin

    suspend fun getPrediction(features: Features): Features {
        return httpClient.post(endpoint) {
            contentType(ContentType.Application.Json)
            body = features
        }
    }
}