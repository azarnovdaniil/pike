package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection

open class Writer {

    val factory: BuilderFactory = BuilderFactory()

    private val builder = StringBuilder()

    fun writeOpenBracket() {
        writeString("(")
    }

    fun writeCloseBracket() {
        writeString(")")
    }

    fun writeString(string: String) {
        builder.append(string)
    }

    fun writeRelation(relation: Relation) {
        builder.append("$relation")
    }

    fun writeProjection(projection: Projection<*>) {
        factory.projectionBuilder().build(projection, this)
    }

    fun writeExpr(expr: Expr<Relation>, fullFormat: Boolean = false) {
        factory.exprBuilder(fullFormat).build(expr, this)
    }

    fun writeProjection(projection: Iterable<PropertyIterator<*>>, fullFormat: Boolean) {
        if ("SELECT".contentEquals(builder.toString().trim()) and projection.none()) {
            builder.append("*")
        } else {
            var delim = ""
            for (proj in projection) {
                builder.append(delim)
                delim = ", "

                if (proj is Relation.Property<*, *>) {
                    if (fullFormat) {
                        appendFullPropertyName(proj)
                    } else {
                        appendShortPropertyName(proj)
                    }
                } else {
                    builder.append(proj)
                }
            }
        }
    }

    fun writeStrings(strings: Iterable<String>) {
        var delim = ""
        for (str in strings) {
            builder.append(delim)
            delim = ", "
            builder.append(str)
        }
    }

    fun appendShortPropertyName(property: Relation.Property<*, *>) {
        builder.append("$property")
    }

    fun appendFullPropertyName(property: Relation.Property<*, *>) {
        builder.append("${property.relation}.$property")
    }

    fun appendValue(value: Any?) {
        value?.let { builder.append((value as? String)?.asString() ?: value) } ?: builder.append("NULL")
    }

    private fun String.asString(): String = "\'${this.replace("'", "''")}\'"

    override fun toString(): String {
        return builder.toString()
    }

}