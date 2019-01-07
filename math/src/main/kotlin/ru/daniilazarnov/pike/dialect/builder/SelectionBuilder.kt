package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.core.operation.unary.Selection
import ru.daniilazarnov.pike.dialect.OperationBuilder
import ru.daniilazarnov.pike.dialect.Generator

object SelectionBuilder : OperationBuilder<Selection<*>> {

    override fun build(ast: Selection<*>, generator: Generator) {
        when (ast) {
            !is Join<*, *> -> {
                val expr = ast.expr
                if (expr != null) {
                    generator.writeString("σ")
                    generator.writeExpr(expr)
                }
            }
        }
    }

}