package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.math.Difference
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

class DifferenceBuilder : Builder<Difference<*, *>> {

    override fun build(ast: Difference<*, *>, writer: Writer) {
        writer.writeOpenBracket()
        writer.writeProjection(ast.projection1)
        writer.writeString(" - ")
        writer.writeProjection(ast.projection2)
        writer.writeCloseBracket()
    }

}