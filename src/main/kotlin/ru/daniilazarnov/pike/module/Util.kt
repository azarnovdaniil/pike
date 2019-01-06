package ru.daniilazarnov.pike.module

import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation

object Util {

    fun appendProjection(builder: StringBuilder, projection: Iterable<PropertyIterator<*>>, fullFormat: Boolean) {
        if ("SELECT".contentEquals(builder.toString().trim()) and projection.none()) {
            builder.append("*")
        } else {
            var delim = ""
            for (proj in projection) {
                builder.append(delim)
                delim = ", "

                if (proj is Relation.Property<*, *>) {
                    if (fullFormat) {
                        appendFullPropertyName(builder, proj)
                    } else {
                        appendShortPropertyName(builder, proj)
                    }
                } else {
                    builder.append(proj)
                }
            }
        }
    }

    fun appendStrings(builder: StringBuilder, strings: Iterable<String>) {
        var delim = ""
        for (str in strings) {
            builder.append(delim)
            delim = ", "
            builder.append(str)
        }
    }

    fun appendRelationName(builder: StringBuilder, relation: Relation) {
        builder.append("$relation")
    }

    fun appendShortPropertyName(builder: StringBuilder, property: Relation.Property<*, *>) {
        builder.append("$property")
    }

    fun appendFullPropertyName(builder: StringBuilder, property: Relation.Property<*, *>) {
        builder.append("${property.relation}.$property")
    }

    fun appendValue(builder: StringBuilder, value: Any?) {
        value?.let { builder.append((value as? String)?.asString() ?: value) } ?: builder.append("NULL")
    }

    private fun String.asString(): String = "\'${this.replace("'", "''")}\'"

}