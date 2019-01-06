package ru.daniilazarnov.pike.core.builder

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Intersection
import ru.daniilazarnov.pike.query.Projection

object IntersectionBuilder {

    fun <R : Relation, S : Projection<R>> build(statement: Intersection<R, S>): String {
        val builder = StringBuilder()

        builder.append("(")
        builder.append(SelectionBuilder.build(statement.projection1))
        builder.append(" ∩ ")
        builder.append(SelectionBuilder.build(statement.projection2))
        builder.append(")")

        return builder.toString()
    }

}