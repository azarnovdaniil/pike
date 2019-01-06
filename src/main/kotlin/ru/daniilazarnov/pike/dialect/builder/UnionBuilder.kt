package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.math.Union
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

class UnionBuilder : Builder<Union<*, *>> {

    override fun build(ast: Union<*, *>, writer: Writer) {
        writer.writeString("(")
        writer.writeProjection(ast.projection1)
        writer.writeString(" ∪ ")
        writer.writeProjection(ast.projection2)
        writer.writeString(")")
    }

}