package ru.daniilazarnov.pike.core.builder

import ru.daniilazarnov.pike.core.builder.Util.appendProjection
import ru.daniilazarnov.pike.core.builder.Util.appendRelationName
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Join
import ru.daniilazarnov.pike.query.Projection

object ProjectionBuilder {

    fun <R : Relation> build(ast: Projection<R>): String {
        val builder = StringBuilder()
        val selection = ast.selection

        when (selection) {
            !is Join<out Relation, out Relation> -> {
                val expr = selection.expr
                if (expr != null) {
                    builder.append("σ")
                    ExprBuilder.build(builder, expr, false)
                }
            }
        }

        val projection = ast.projection
        if (!projection.none()) {
            builder.append("π")
            builder.append("(")
            appendProjection(builder, projection, false)
            builder.append(")")
        }

        builder.append("(")
        appendRelationName(builder, selection.relation)

        when (selection) {
            is Join<out Relation, out Relation> -> {
                if (selection.type == Join.JoinType.NATURAL) {
                    builder.append(" ⋈ ")
                    appendRelationName(builder, selection.relation2)
                } else {
                    if (selection.type == Join.JoinType.OUTER) builder.append(" OUTER")
                    builder.append(" ⋈")
                    val condition = selection.condition
                    if (condition != null) {
                        ExprBuilder.build(builder, condition, true)
                    }
                    builder.append(" ")
                    appendRelationName(builder, selection.relation2)
                }
            }
        }

        builder.append(")")

        return builder.toString()
    }

}