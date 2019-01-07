package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.dialect.OperationBuilder
import ru.daniilazarnov.pike.dialect.Generator

object ProjectionBuilder : OperationBuilder<Projection<*>> {

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