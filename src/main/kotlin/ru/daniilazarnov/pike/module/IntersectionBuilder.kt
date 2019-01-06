package ru.daniilazarnov.pike.module

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.math.Intersection
import ru.daniilazarnov.pike.core.query.Projection

object IntersectionBuilder {

    fun <R : Relation, S : Projection<R>> build(statement: Intersection<R, S>): String {
        val builder = StringBuilder()

        builder.append("(")
        builder.append(ProjectionBuilder.build(statement.projection1))
        builder.append(" ∩ ")
        builder.append(ProjectionBuilder.build(statement.projection2))
        builder.append(")")

        return builder.toString()
    }

}