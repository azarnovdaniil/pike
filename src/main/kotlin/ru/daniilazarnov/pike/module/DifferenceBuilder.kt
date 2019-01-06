package ru.daniilazarnov.pike.module

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.math.Difference
import ru.daniilazarnov.pike.core.query.Projection

object DifferenceBuilder {

    fun <R : Relation, S : Projection<R>> build(statement: Difference<R, S>): String {
        val builder = StringBuilder()

        builder.append("(")
        builder.append(ProjectionBuilder.build(statement.projection1))
        builder.append(" - ")
        builder.append(ProjectionBuilder.build(statement.projection2))
        builder.append(")")

        return builder.toString()
    }

}