package altline.insurance

import kotlinx.html.*

fun HTML.index() {
    head {
        title("Insurance predictor")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/RUAP-insurance_cost.js") {}
    }
}