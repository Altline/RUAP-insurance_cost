package view

import Features
import PredictionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.div

external interface BaseProps : Props {
    var coroutineScope: CoroutineScope
    var predictionService: PredictionService
}

external interface BaseState : State

class Base : RComponent<BaseProps, BaseState>() {

    override fun RBuilder.render() {
        div {
            child(featureInput) {
                attrs.onSubmit = { features ->
                    props.coroutineScope.launch {
                        val prediction = submitForPrediction(features)
                        println(prediction)
                    }
                }
            }
        }
    }

    private suspend fun submitForPrediction(features: Features): Features {
        return props.predictionService.getPrediction(features)
    }
}