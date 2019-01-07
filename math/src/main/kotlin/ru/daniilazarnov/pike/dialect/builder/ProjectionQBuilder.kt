package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.unary.Projection
import ru.daniilazarnov.pike.dialect.QBuilder
import ru.daniilazarnov.pike.dialect.Generator

object ProjectionQBuilder : QBuilder<Projection<*>> {

    override fun build(ast: Projection<*>, generator: Generator) {

        val selection = ast.selection
        when (selection) {
            !is Join<*, *> -> {
                generator.writeSelection(selection)
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
            is Join<*, *> -> {
                generator.writeJoin(selection)
            }
        }

        generator.writeCloseBracket()
    }

}