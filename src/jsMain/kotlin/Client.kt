import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        val root = document.getElementById("root")!!
        render(root) {
            +"Kotlin/JS"
        }
    }
}
