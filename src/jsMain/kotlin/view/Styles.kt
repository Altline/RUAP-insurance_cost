package view

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import styled.StyleSheet

object Styles : StyleSheet("Styles", isStatic = true) {
    val baseLayout by css {
        position = Position.absolute
        top = 50.pct
        left = 50.pct
        transform {
            translate(-50.pct, -50.pct)
        }
        display = Display.flex
        flexDirection = FlexDirection.column
        alignItems = Align.center
    }

    val topBlock by css {
        margin(vertical = 10.px)
        padding(16.px)
        borderRadius = 8.px
        backgroundColor = Color("#e1ffe9")
        boxShadow(Color.lightGray, 4.px, 4.px, 4.px)
    }

    val inputBlock by css {
        margin(top = 8.px)
        display = Display.flex
        justifyContent = JustifyContent.spaceBetween
        descendants("input") {
            margin(left = 10.px)
            textAlign = TextAlign.right
        }
    }

    val submitBlock by css {
        display = Display.flex
        descendants("input") {
            margin(top = 20.px, horizontal = LinearDimension.auto, bottom = 0.px)
        }
    }

    val title by css {
        fontFamily = "sans-serif"
        color = Color.darkGreen
    }
}