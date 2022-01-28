import kotlinx.serialization.Serializable

@Serializable
data class Features(
    var age: Int = 0,
    var diabetes: Boolean = false,
    var bloodPressureProblems: Boolean = false,
    var anyTransplants: Boolean = false,
    var anyChronicDiseases: Boolean = false,
    var height: Int = 0,
    var weight: Int = 0,
    var knownAllergies: Boolean = false,
    var historyOfCancerInFamily: Boolean = false,
    var numberOfMajorSurgeries: Int = 0,
    var premiumPrice: Double = 0.0,
    var scoredLabelMean: Double = 0.0,
    var scoredLabelStandardDeviation: Double = 0.0
) {
    fun asFeatureArray() = arrayOf<Number>(
        age,
        diabetes.toInt(),
        bloodPressureProblems.toInt(),
        anyTransplants.toInt(),
        anyChronicDiseases.toInt(),
        height,
        weight,
        knownAllergies.toInt(),
        historyOfCancerInFamily.toInt(),
        numberOfMajorSurgeries,
        0.0
    )
}

fun Features(rawFeatures: Collection<String>): Features {
    with(rawFeatures.iterator()) {
        val age = next().toInt()
        val diabetes = next().toBooleanNumeric()
        val bloodPressureProblems = next().toBooleanNumeric()
        val anyTransplants = next().toBooleanNumeric()
        val anyChronicDiseases = next().toBooleanNumeric()
        val height = next().toInt()
        val weight = next().toInt()
        val knownAllergies = next().toBooleanNumeric()
        val historyOfCancerInFamily = next().toBooleanNumeric()
        val numberOfMajorSurgeries = next().toInt()
        val premiumPrice = next().toDouble()
        val scoredLabelMean = next().toDouble()
        val scoredLabelStandardDeviation = next().toDouble()

        return Features(
            age,
            diabetes,
            bloodPressureProblems,
            anyTransplants,
            anyChronicDiseases,
            height,
            weight,
            knownAllergies,
            historyOfCancerInFamily,
            numberOfMajorSurgeries,
            premiumPrice,
            scoredLabelMean,
            scoredLabelStandardDeviation
        )
    }
}

fun Boolean.toInt() = if (this) 1 else 0
fun String.toBooleanNumeric() = this != "0"
