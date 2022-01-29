package view

import Features
import PredictionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import react.Props
import react.fc
import react.useState
import styled.css
import styled.styledDiv
import styled.styledH1

external interface BaseProps : Props {
    var coroutineScope: CoroutineScope
    var predictionService: PredictionService
}

val base = fc<BaseProps> { props ->
    var scoredFeatures: Features? by useState(null)
    var processing: Boolean by useState(false)

    styledDiv {
        css { +Styles.baseLayout }

        styledH1 {
            css { +Styles.title }
            +"Insurance predictor"
        }

        styledDiv {
            css { +Styles.topBlock }
            featureInput { features ->
                props.coroutineScope.launch {
                    processing = true
                    val sf = props.predictionService.getPrediction(features)
                    processing = false
                    scoredFeatures = sf
                }
            }
        }
        if (scoredFeatures != null) {
            styledDiv {
                css { +Styles.topBlock }
                scoreView(scoredFeatures)
            }
        }
        if (processing) {
            styledDiv {
                css { +Styles.topBlock }
                +"Processing..."
            }
        }
    }
}
