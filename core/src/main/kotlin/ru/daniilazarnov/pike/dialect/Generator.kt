package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.core.operation.unary.Selection

abstract class Generator {

    abstract val factory: BuilderFactory

    protected val builder = StringBuilder()

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

    fun writeSelection(selection: Selection<*>) {
        factory.selectionBuilder().build(selection, this)
    }

    fun writeJoin(join: Join<*, *>) {
        factory.joinBuilder().build(join, this)
    }

    fun writeExpr(expr: Expr<*>, fullFormat: Boolean = false) {
        factory.exprBuilder(fullFormat).build(expr, this)
    }

    open fun writeAny(any: Any?, fullFormat: Boolean = false) {
        when (any) {
            is Expr<*> -> writeExpr(any, fullFormat)

            is Relation.Property<*, *> -> writeProperty(any, fullFormat)

            else -> {
                any?.let { builder.append((any as? String)?.asString() ?: any) } ?: builder.append("NULL")
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

    fun writeProperty(property: Relation.Property<*, *>, fullFormat: Boolean = false) {
        if (fullFormat) {
            builder.append("${property.relation}.$property")
        } else {
            builder.append("$property")
        }
    }

    fun writeProjection(projection: Iterable<PropertyIterator<*>>, fullFormat: Boolean) {
        var delim = ""
        for (proj in projection) {
            builder.append(delim)
            delim = ", "

            if (proj is Relation.Property<*, *>) {
                writeProperty(proj, fullFormat)
            } else {
                builder.append(proj)
            }
        }
    }

    fun String.asString(): String = "\'${this.replace("'", "''")}\'"

    override fun toString(): String {
        return builder.toString()
    }

}