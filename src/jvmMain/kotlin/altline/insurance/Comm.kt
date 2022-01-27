package altline.insurance

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val baseUrl = "https://ussouthcentral.services.azureml.net/workspaces/ca191a792ede4730a7fd88a9666ab74f/services/5f6ba3271ae84d478eb30ec1b6dba826/"

val clientBuilder = OkHttpClient.Builder().apply {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    addInterceptor(loggingInterceptor)
}

val retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(clientBuilder.build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
