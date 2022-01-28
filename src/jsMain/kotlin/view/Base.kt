package view

import Features
import PredictionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import react.Props
import react.dom.div
import react.fc
import react.useState

external interface BaseProps : Props {
    var coroutineScope: CoroutineScope
    var predictionService: PredictionService
}

val base = fc<BaseProps> { props ->
    var scoredFeatures: Features? by useState(null)

    div {
        featureInput { features ->
            props.coroutineScope.launch {
                val sf = props.predictionService.getPrediction(features)
                scoredFeatures = sf
            }
        }
        scoreView(scoredFeatures)
    }
}
