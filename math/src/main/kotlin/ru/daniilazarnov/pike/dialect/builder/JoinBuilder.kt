package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.dialect.OperationBuilder
import ru.daniilazarnov.pike.dialect.Generator

object JoinBuilder : OperationBuilder<Join<*, *>> {

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
                generator.writeCloseBracket()
                build(selection, generator)
            }

        }
    }

}