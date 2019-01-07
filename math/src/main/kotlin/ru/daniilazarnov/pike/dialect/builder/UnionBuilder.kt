package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.set.Union
import ru.daniilazarnov.pike.dialect.OperationBuilder
import ru.daniilazarnov.pike.dialect.Generator

object UnionBuilder : OperationBuilder<Union<*, *>> {

    override fun build(ast: Union<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" ∪ ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}