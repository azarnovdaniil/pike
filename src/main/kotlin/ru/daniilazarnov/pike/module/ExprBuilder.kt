package ru.daniilazarnov.pike.module

import ru.daniilazarnov.pike.module.Util.appendValue
import ru.daniilazarnov.pike.core.data.*

object ExprBuilder {

    fun build(builder: StringBuilder, value: Expr<Relation>, fullFormat: Boolean = true) {
        appendPredicate(builder, value, fullFormat)
    }

    private fun appendPredicate(builder: StringBuilder, value: Any?, fullFormat: Boolean = true) {
        when (value) {
            is Relation.Property<*, *> -> if (fullFormat) {
                Util.appendFullPropertyName(builder, value)
            } else {
                Util.appendShortPropertyName(builder, value)
            }

            is NotExpr<*> -> {
                builder.append("(NOT ")
                appendPredicate(builder, value.param, fullFormat)
                builder.append(")")
            }

            is AndExpr<*> -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" AND ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is OrExpr<*> -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" OR ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is EqExpr<*> -> {
                builder.append('(')
                if (value.right != null) {
                    appendPredicate(builder, value.left, fullFormat)
                    builder.append(" = ")
                    appendPredicate(builder, value.right, fullFormat)
                } else {
                    appendPredicate(builder, value.left, fullFormat)
                    builder.append(" IS NULL")
                }
                builder.append(')')
            }

            is LtExpr<*> -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" < ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is LteExpr<*> -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" <= ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is GtExpr<*> -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" > ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is GteExpr<*> -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" ≥ ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            else -> appendValue(builder, value)
        }
    }

}