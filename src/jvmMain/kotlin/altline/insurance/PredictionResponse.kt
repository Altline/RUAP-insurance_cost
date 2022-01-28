package altline.insurance

import Features
import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("Results")
    val results: Map<String, OutputWrapper>
) {
    data class OutputWrapper(
        @SerializedName("value")
        val value: ValueWrapper
    ) {
        data class ValueWrapper(
            @SerializedName("Values")
            val values: Collection<Collection<String>>
        )
    }

    fun toFeatures(): Features {
        val values = results["output1"]?.value?.values?.first()
            ?: throw UnexpectedFormatException("Could not find property \"output1\" in results body.")

        return Features(values)
    }
}