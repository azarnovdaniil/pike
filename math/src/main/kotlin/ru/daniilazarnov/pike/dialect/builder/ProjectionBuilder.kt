package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

object ProjectionBuilder : Builder<Projection<*>> {

    override fun build(ast: Projection<*>, generator: Generator) {
        generator.writeOpenBracket()

        val selection = ast.selection
        when (selection) {
            !is Join<*, *> -> generator.writeSelection(selection)
        }

        val projection = ast.projection
        if (!projection.none()) {
            generator.writeString("π")
            generator.writeOpenBracket()
            generator.writeProjection(projection, false)
            generator.writeCloseBracket()
        }

        generator.writeRelation(selection.relation)

        when (selection) {
            is Join<*, *> -> {
                generator.writeJoin(selection)
                generator.writeCloseBracket()
            }
        }

        generator.writeCloseBracket()

    }

}