package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

open class ProjectionBuilder : Builder<Projection<*>> {

    override fun build(ast: Projection<*>, writer: Writer) {
        val selection = ast.selection

        when (selection) {
            !is Join<out Relation, out Relation> -> {
                val expr = selection.expr
                if (expr != null) {
                    writer.writeString("σ")
                    writer.writeExpr(expr)
                }
            }
        }

        val projection = ast.projection
        if (!projection.none()) {
            writer.writeString("π")
            writer.writeOpenBracket()
            writer.writeProjection(projection, false)
            writer.writeCloseBracket()
        }

        writer.writeOpenBracket()
        writer.writeRelation(selection.relation)

        when (selection) {
            is Join<out Relation, out Relation> -> {
                if (selection.type == Join.JoinType.NATURAL) {
                    writer.writeString(" ⋈ ")
                    writer.writeRelation(selection.relation2)
                } else {
                    if (selection.type == Join.JoinType.OUTER) {
                        writer.writeString(" OUTER")
                    }
                    writer.writeString(" ⋈")
                    val condition = selection.condition
                    if (condition != null) {
                        writer.writeExpr(condition, true)
                    }
                    writer.writeString(" ")
                    writer.writeRelation(selection.relation2)
                }
            }
        }

        writer.writeCloseBracket()
    }

}