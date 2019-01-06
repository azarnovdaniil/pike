package ru.daniilazarnov.pike.core.builder

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.builder.Util.appendProjection
import ru.daniilazarnov.pike.core.builder.Util.appendRelationName
import ru.daniilazarnov.pike.query.Projection

object ProjectionBuilder {

    fun <R : Relation> build(statement: Projection<R>, fullFormat: Boolean = true): String {
        val builder = StringBuilder()

        val expr = statement.selection.expr
        if (expr != null) {
            builder.append("σ")
            ExprBuilder.build(builder, expr, false)
        }
        val projection = statement.projection
        if (!projection.none()) {
            builder.append("π")
            builder.append("(")
            appendProjection(builder, projection, false)
            builder.append(")")
        }

        builder.append("(")
        appendRelationName(builder, statement.selection.relation)

//        builder.append("(")
//        appendRelationName(builder, statement.join.selection.relation)
//        if (statement.join.type == Join.JoinType.NATURAL) {
//            builder.append(" ⋈ ")
//            appendRelationName(builder, statement.join.relation2)
//        } else {
//            if (statement.join.type == Join.JoinType.OUTER) builder.append(" OUTER")
//            builder.append(" ⋈")
//            appendPredicate(builder, statement.join.condition, true)
//            builder.append(" ")
//            appendRelationName(builder, statement.join.relation2)
//        }
//        builder.append(")")

        builder.append(")")

        return builder.toString()
    }

}