package view

import Features
import react.Props
import react.RBuilder
import react.dom.div
import react.fc

fun RBuilder.scoreView(scoredFeatures: Features? = null) {
    child(scoreView) {
        attrs.scoredFeatures = scoredFeatures
    }
}

external interface ScoreViewProps : Props {
    var scoredFeatures: Features?
}

val scoreView = fc<ScoreViewProps> { props ->
    div {
        val scoreMean = props.scoredFeatures?.scoredLabelMean?.toString() ?: "-"
        +"Score mean: $scoreMean"
    }
    div {
        val scoreDeviation = props.scoredFeatures?.scoredLabelStandardDeviation?.toString() ?: "-"
        +"Score standard deviation $scoreDeviation"
    }
}
