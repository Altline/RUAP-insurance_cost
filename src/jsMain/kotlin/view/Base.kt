package view

import PredictionService
import kotlinx.coroutines.CoroutineScope
import react.Props
import react.RBuilder
import react.RComponent
import react.State

external interface BaseProps : Props {
    var coroutineScope: CoroutineScope
    var predictionService: PredictionService
}

external interface BaseState : State

class Base : RComponent<BaseProps, BaseState>() {

    override fun RBuilder.render() {

    }
}