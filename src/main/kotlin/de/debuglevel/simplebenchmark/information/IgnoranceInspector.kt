package de.debuglevel.simplebenchmark.information

import com.fasterxml.jackson.databind.introspect.AnnotatedMember
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector

internal class IgnoranceIntrospector(
    private val ignored: Map<Class<out Any>, Set<String>>
) : JacksonAnnotationIntrospector() {
    override fun hasIgnoreMarker(m: AnnotatedMember): Boolean {
        return when {
            super.hasIgnoreMarker(m) -> true
            ignored.containsKey(m.declaringClass) -> ignored[m.declaringClass]!!.contains(m.name)
            else -> false
        }
    }
}