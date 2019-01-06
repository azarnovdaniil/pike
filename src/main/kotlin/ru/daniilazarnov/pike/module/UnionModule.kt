package ru.daniilazarnov.pike.module

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.core.math.Union

class UnionModule<R : Relation> : Module<Union<R, out Projection<R>>> {

    override fun build(ast: Union<R, out Projection<R>>): String {
        val builder = StringBuilder()

        builder.append("(")
        builder.append(ProjectionBuilder.build(ast.projection1))
        builder.append(" ∪ ")
        builder.append(ProjectionBuilder.build(ast.projection2))
        builder.append(")")

        return builder.toString()
    }

}