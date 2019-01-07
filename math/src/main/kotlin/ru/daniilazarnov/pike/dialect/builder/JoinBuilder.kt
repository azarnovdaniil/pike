package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.dialect.OperationBuilder
import ru.daniilazarnov.pike.dialect.Generator

object JoinBuilder : OperationBuilder<Join<*, *>> {

    override fun build(ast: Join<*, *>, generator: Generator) {
        when(ast.type) {
            Join.JoinType.NATURAL -> {
                generator.writeString(" ⋈ ")
                generator.writeRelation(ast.relation2)
            }
            Join.JoinType.EQUI -> {
                generator.writeString(" ⋈")
                val condition = ast.condition
                if (condition != null) {
                    generator.writeExpr(condition, true)
                }
                generator.writeString(" ")
                generator.writeRelation(ast.relation2)
            }
            Join.JoinType.LEFT -> {
                generator.writeString(" ⋉ ")
                generator.writeRelation(ast.relation2)
            }
            Join.JoinType.RIGHT -> {
                generator.writeString(" ⋊ ")
                generator.writeRelation(ast.relation2)
            }
            Join.JoinType.FULL -> {
                generator.writeString(" ⟗ ")
                generator.writeRelation(ast.relation2)
            }
            Join.JoinType.LEFT_ANTI -> {
                generator.writeString(" ▷ ")
                generator.writeRelation(ast.relation2)
            }
            Join.JoinType.RIGHT_ANTI -> {
                generator.writeString(" ◁ ")
                generator.writeRelation(ast.relation2)
            }
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