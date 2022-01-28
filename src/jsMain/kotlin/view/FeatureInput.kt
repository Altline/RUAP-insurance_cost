package view

import Features
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.Props
import react.RBuilder
import react.dom.*
import react.fc
import react.useState

external interface FeatureInputProps : Props {
    var onSubmit: (Features) -> Unit
}

val featureInput = fc<FeatureInputProps> { props ->
    val features by useState(Features())

    form {
        attrs.onSubmit = {
            it.preventDefault()
            props.onSubmit(features)
        }

        intInput("age", "Age") {
            features.age = it
        }
        intInput("height", "Height") {
            features.height = it
        }
        intInput("weight", "Weight") {
            features.weight = it
        }
        booleanInput("allergies", "Known allergies") {
            features.knownAllergies = it
        }
        booleanInput("diabetes", "Diabetes") {
            features.diabetes = it
        }
        booleanInput("bpp", "Blood pressure problems") {
            features.bloodPressureProblems = it
        }
        booleanInput("chronic", "Any chronic diseases") {
            features.anyChronicDiseases = it
        }
        booleanInput("cancer", "History of cancer in family") {
            features.historyOfCancerInFamily = it
        }
        booleanInput("transplants", "Any transplants") {
            features.anyTransplants = it
        }
        intInput( "surgeries", "Number of major surgeries") {
            features.numberOfMajorSurgeries = it
        }

        input(InputType.submit) {
            attrs.value = "Submit"
        }
    }
}

fun RBuilder.featureInput(onSubmit: (Features) -> Unit) {
    child(featureInput) {
        attrs.onSubmit = onSubmit
    }
}

private fun RBuilder.intInput(id: String, labelText: String, onChangeValue: (Int) -> Unit) {
    div {
        label {
            +labelText
            attrs.htmlFor = id
        }
        input(InputType.number) {
            attrs.id = id
            attrs.onChangeFunction = { event ->
                event.runCatching {
                    (event.target as HTMLInputElement).value.toInt()
                }.onSuccess {
                    onChangeValue(it)
                }
            }
        }
    }
}

private fun RBuilder.booleanInput(id: String, labelText: String, onChangeValue: (Boolean) -> Unit) {
    div {
        label {
            +labelText
            attrs.htmlFor = id
        }
        input(InputType.checkBox) {
            attrs.id = id
            attrs.onChangeFunction = { event ->
                val newValue = (event.target as HTMLInputElement).checked
                onChangeValue(newValue)
            }
        }
    }
}