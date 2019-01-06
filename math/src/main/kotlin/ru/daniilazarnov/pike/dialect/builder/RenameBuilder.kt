package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.update.Rename
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

object RenameBuilder : Builder<Rename<*>> {

    override fun build(ast: Rename<*>, generator: Generator) {
        generator.writeString("ρ")
        generator.writeOpenBracket()
        generator.writeProjection(ast.properties, false)
        generator.writeString(" -> ")
        generator.writeStrings(ast.newNames)
        generator.writeCloseBracket()

        generator.writeOpenBracket()
        generator.writeRelation(ast.relation)
        generator.writeCloseBracket()
    }

}