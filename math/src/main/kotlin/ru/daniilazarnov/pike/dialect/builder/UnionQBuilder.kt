package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.set.Union
import ru.daniilazarnov.pike.dialect.QBuilder
import ru.daniilazarnov.pike.dialect.Generator

object UnionQBuilder : QBuilder<Union<*, *>> {

    override fun build(ast: Union<*, *>, generator: Generator) {
        generator.writeString("(")
        generator.writeProjection(ast.projection1)
        generator.writeString(" ∪ ")
        generator.writeProjection(ast.projection2)
        generator.writeString(")")
    }

}