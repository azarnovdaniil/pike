package ru.daniilazarnov.pike.core.builder

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Projection
import ru.daniilazarnov.pike.query.Union

object UnionBuilder {

    fun <T : Relation, S : Projection<T>> build(statement: Union<T, S>): String {
        val builder = StringBuilder()

        builder.append("(")
        builder.append(SelectionBuilder.build(statement.projection1))
        builder.append(" ∪ ")
        builder.append(SelectionBuilder.build(statement.projection2))
        builder.append(")")

        return builder.toString()
    }

}