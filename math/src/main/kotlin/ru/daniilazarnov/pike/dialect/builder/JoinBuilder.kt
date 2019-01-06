package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

object JoinBuilder : Builder<Join<*, *>> {

    override fun build(ast: Join<*, *>, generator: Generator) {
        if (ast.type == Join.JoinType.NATURAL) {
            generator.writeString(" ⋈ ")
            generator.writeRelation(ast.relation2)
        } else {
            generator.writeString(" ⋈")
            val condition = ast.condition
            if (condition != null) {
                generator.writeExpr(condition, true)
            }
            generator.writeString(" ")
            generator.writeRelation(ast.relation2)
        }
        val selection = ast.selection
        when (selection) {
            is Join<*, *> -> {
                build(selection, generator)
            }

        }
    }

}