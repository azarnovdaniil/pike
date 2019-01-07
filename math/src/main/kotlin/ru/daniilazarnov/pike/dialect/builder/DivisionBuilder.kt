package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.binary.Division
import ru.daniilazarnov.pike.dialect.Generator
import ru.daniilazarnov.pike.dialect.OperationBuilder

object DivisionBuilder : OperationBuilder<Division<*, *>> {

    override fun build(ast: Division<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" ÷ ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}