package altline.insurance

import Features

data class PredictionRequest(
    val inputs: Map<String, InputWrapper>,
    val globalParameters: Map<String, String> = emptyMap()
) {
    data class InputWrapper(
        val columnNames: Array<String>,
        val values: Array<Array<Number>>
    )

    companion object {
        fun fromFeatures(features: Features): PredictionRequest {
            return with(features) {
                val columnNames = arrayOf(
                    "Age",
                    "Diabetes",
                    "BloodPressureProblems",
                    "AnyTransplants",
                    "AnyChronicDiseases",
                    "Height",
                    "Weight",
                    "KnownAllergies",
                    "HistoryOfCancerInFamily",
                    "NumberOfMajorSurgeries",
                    "PremiumPrice"
                )
                val values = arrayOf(features.asFeatureArray())
                val inputs = InputWrapper(columnNames, values)
                PredictionRequest(mapOf("input1" to inputs))
            }
        }
    }
}