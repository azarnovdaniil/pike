package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.math.Union
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

class UnionBuilder : Builder<Union<*, *>> {

    override fun build(ast: Union<*, *>, generator: Generator) {
        generator.writeString("(")
        generator.writeProjection(ast.projection1)
        generator.writeString(" ∪ ")
        generator.writeProjection(ast.projection2)
        generator.writeString(")")
    }

}