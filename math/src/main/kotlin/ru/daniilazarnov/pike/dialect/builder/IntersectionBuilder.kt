package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.math.Intersection
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

object IntersectionBuilder : Builder<Intersection<*, *>> {

    override fun build(ast: Intersection<*, *>, generator: Generator) {
        generator.writeOpenBracket()
        generator.writeProjection(ast.projection1)
        generator.writeString(" ∩ ")
        generator.writeProjection(ast.projection2)
        generator.writeCloseBracket()
    }

}