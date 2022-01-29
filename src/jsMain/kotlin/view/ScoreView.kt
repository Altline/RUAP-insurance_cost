package view

import Features
import react.Props
import react.RBuilder
import react.dom.div
import react.fc
import kotlin.math.round

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
        val scoreMean = props.scoredFeatures?.scoredLabelMean?.rounded?.toString() ?: "-"
        +"Score mean: $scoreMean"
    }
    div {
        val scoreDeviation = props.scoredFeatures?.scoredLabelStandardDeviation?.rounded?.toString() ?: "-"
        +"Score standard deviation $scoreDeviation"
    }
}

private val Double.rounded get() = round(this/100.0) * 100.0
