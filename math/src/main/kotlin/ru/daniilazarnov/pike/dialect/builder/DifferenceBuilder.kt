package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.set.Difference
import ru.daniilazarnov.pike.dialect.OperationBuilder
import ru.daniilazarnov.pike.dialect.Generator

object DifferenceBuilder : OperationBuilder<Difference<*, *>> {

    override fun build(ast: Difference<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" - ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}