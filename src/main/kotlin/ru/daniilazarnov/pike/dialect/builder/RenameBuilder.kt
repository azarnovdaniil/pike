package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.update.Rename
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

class RenameBuilder : Builder<Rename<*>> {

    override fun build(ast: Rename<*>, writer: Writer) {
        writer.writeString("ρ")
        writer.writeOpenBracket()
        writer.writeProjection(ast.properties, false)
        writer.writeString(" -> ")
        writer.writeStrings(ast.newNames)
        writer.writeCloseBracket()

        writer.writeOpenBracket()
        writer.writeRelation(ast.relation)
        writer.writeCloseBracket()
    }

}