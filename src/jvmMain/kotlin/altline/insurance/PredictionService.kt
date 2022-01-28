package altline.insurance

import Features
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

class PredictionService {
    private val log by logger()

    private val authKey = "Bearer 0k5YRGcOse+FdHPwuJ60Eai8X+YaW28JQ1OY75G1QfvIpwqS8bgADLrUG1UPvBZixKbUHtBVdpymXHJBDgPsMA=="

    private val webService by lazy { retrofit.create(PredictionWebService::class.java) }

    suspend fun getPrediction(features: Features): Features? {
        val requestBody = toRequestBody(features)
        val responseBody = webService.getPrediction(requestBody, authKey)
        return toFeatures(responseBody)
    }

    private fun toRequestBody(features: Features): PredictionRequest {
        return PredictionRequest.fromFeatures(features)
    }

    private fun toFeatures(responseBody: PredictionResponse): Features? {
        return responseBody.runCatching {
            toFeatures()
        }.onFailure {
            log.error("Could not convert web response to feature set.", it)
        }.getOrNull()
    }
}

private interface PredictionWebService {
    @POST("execute?api-version=2.0")
    suspend fun getPrediction(
        @Body requestBody: PredictionRequest,
        @Header("Authorization") authKey: String
    ): PredictionResponse
}
