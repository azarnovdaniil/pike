package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

object ProjectionBuilder : Builder<Projection<*>> {

    override fun build(ast: Projection<*>, generator: Generator) {
        val selection = ast.selection

        when (selection) {
            !is Join<out Relation, out Relation> -> {
                val expr = selection.expr
                if (expr != null) {
                    generator.writeString("σ")
                    generator.writeExpr(expr)
                }
            }
        }

        val projection = ast.projection
        if (!projection.none()) {
            generator.writeString("π")
            generator.writeOpenBracket()
            generator.writeProjection(projection, false)
            generator.writeCloseBracket()
        }

        generator.writeOpenBracket()
        generator.writeRelation(selection.relation)

        when (selection) {
            is Join<out Relation, out Relation> -> {
                if (selection.type == Join.JoinType.NATURAL) {
                    generator.writeString(" ⋈ ")
                    generator.writeRelation(selection.relation2)
                } else {
                    if (selection.type == Join.JoinType.OUTER) {
                        generator.writeString(" OUTER")
                    }
                    generator.writeString(" ⋈")
                    val condition = selection.condition
                    if (condition != null) {
                        generator.writeExpr(condition, true)
                    }
                    generator.writeString(" ")
                    generator.writeRelation(selection.relation2)
                }
            }
        }

        generator.writeCloseBracket()
    }

}