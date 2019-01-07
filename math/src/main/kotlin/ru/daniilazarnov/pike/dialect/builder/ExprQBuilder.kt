package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.*
import ru.daniilazarnov.pike.dialect.QBuilder
import ru.daniilazarnov.pike.dialect.Generator

class ExprQBuilder(private val fullFormat: Boolean = true) : QBuilder<Expr<*>> {

    override fun build(ast: Expr<*>, generator: Generator) {
        when (ast) {

            is NotExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeString("¬ ")
                generator.writeAny(ast.param, fullFormat)
                generator.writeCloseBracket()
            }

            is AndExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeAny(ast.left, fullFormat)
                generator.writeString(" ∧ ")
                generator.writeAny(ast.right, fullFormat)
                generator.writeCloseBracket()
            }

            is OrExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeAny(ast.left, fullFormat)
                generator.writeString(" ∨ ")
                generator.writeAny(ast.right, fullFormat)
                generator.writeCloseBracket()
            }

            is EqExpr<*> -> {
                generator.writeOpenBracket()
                if (ast.right != null) {
                    generator.writeAny(ast.left, fullFormat)
                    generator.writeString(" = ")
                    generator.writeAny(ast.right, fullFormat)
                } else {
                    generator.writeAny(ast.left, fullFormat)
                    generator.writeString(" = null")
                }
                generator.writeCloseBracket()
            }

            is LtExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeAny(ast.left, fullFormat)
                generator.writeString(" < ")
                generator.writeAny(ast.right, fullFormat)
                generator.writeCloseBracket()
            }

            is LteExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeAny(ast.left, fullFormat)
                generator.writeString(" ≤ ")
                generator.writeAny(ast.right, fullFormat)
                generator.writeCloseBracket()
            }

            is GtExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeAny(ast.left, fullFormat)
                generator.writeString(" > ")
                generator.writeAny(ast.right, fullFormat)
                generator.writeCloseBracket()
            }

            is GteExpr<*> -> {
                generator.writeOpenBracket()
                generator.writeAny(ast.left, fullFormat)
                generator.writeString(" ≥ ")
                generator.writeAny(ast.right, fullFormat)
                generator.writeCloseBracket()
            }
        }
    }

}
