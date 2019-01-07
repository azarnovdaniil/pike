package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.set.Intersection
import ru.daniilazarnov.pike.dialect.QBuilder
import ru.daniilazarnov.pike.dialect.Generator

object IntersectionQBuilder : QBuilder<Intersection<*, *>> {

    override fun build(ast: Intersection<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" ∩ ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}