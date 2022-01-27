package altline.insurance

import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

// https://stackoverflow.com/a/34462577/6640693
fun <R : Any> R.logger() = lazy { LoggerFactory.getLogger(unwrapCompanionClass(this.javaClass).name) }
fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return ofClass.enclosingClass?.takeIf {
        ofClass.enclosingClass.kotlin.companionObject?.java == ofClass
    } ?: ofClass
}