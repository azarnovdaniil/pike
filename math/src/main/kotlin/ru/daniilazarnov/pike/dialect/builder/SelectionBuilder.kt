package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.query.Selection
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Generator

object SelectionBuilder : Builder<Selection<*>> {

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