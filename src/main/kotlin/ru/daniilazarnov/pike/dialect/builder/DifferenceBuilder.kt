package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.math.Difference
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

class DifferenceBuilder : Builder<Difference<*, *>> {

    override fun build(ast: Difference<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" - ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}