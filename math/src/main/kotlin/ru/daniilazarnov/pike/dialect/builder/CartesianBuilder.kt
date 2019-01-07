package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.set.Cartesian
import ru.daniilazarnov.pike.dialect.Generator
import ru.daniilazarnov.pike.dialect.OperationBuilder

object CartesianBuilder : OperationBuilder<Cartesian<*, *>> {

    override fun build(ast: Cartesian<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" × ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}