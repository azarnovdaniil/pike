package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.math.Intersection
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

class IntersectionBuilder : Builder<Intersection<*, *>> {

    override fun build(ast: Intersection<*, *>, writer: Writer) {
        writer.writeOpenBracket()
        writer.writeProjection(ast.projection1)
        writer.writeString(" ∩ ")
        writer.writeProjection(ast.projection2)
        writer.writeCloseBracket()
    }

}