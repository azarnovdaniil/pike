package ru.daniilazarnov.pike.core.builder

import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.update.Rename

object RenameBuilder {

    fun <R : Relation> build(ast: Rename<R>): String {
        val builder = StringBuilder()

        builder.append("ρ")

        builder.append("(")
        Util.appendProjection(builder, ast.properties, false)


        builder.append(" -> ")
        Util.appendStrings(builder, ast.newNames)
        builder.append(")")

        builder.append("(")
        Util.appendRelationName(builder, ast.relation)
        builder.append(")")


        return builder.toString()
    }

}