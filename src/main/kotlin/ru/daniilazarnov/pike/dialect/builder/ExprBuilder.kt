package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.*
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

class ExprBuilder(private val fullFormat: Boolean = true) : Builder<Expr<*>> {

    override fun build(ast: Expr<*>, writer: Writer) {
        when (ast) {

            is NotExpr<*> -> {
                writer.writeString("(NOT ")
                writer.writeAny(ast.param, fullFormat)
                writer.writeCloseBracket()
            }

            is AndExpr<*> -> {
                writer.writeOpenBracket()
                writer.writeAny(ast.left, fullFormat)
                writer.writeString(" AND ")
                writer.writeAny(ast.right, fullFormat)
                writer.writeCloseBracket()
            }

            is OrExpr<*> -> {
                writer.writeOpenBracket()
                writer.writeAny(ast.left, fullFormat)
                writer.writeString(" OR ")
                writer.writeAny(ast.right, fullFormat)
                writer.writeCloseBracket()
            }

            is EqExpr<*> -> {
                writer.writeOpenBracket()
                if (ast.right != null) {
                    writer.writeAny(ast.left, fullFormat)
                    writer.writeString(" = ")
                    writer.writeAny(ast.right, fullFormat)
                } else {
                    writer.writeAny(ast.left, fullFormat)
                    writer.writeString(" IS NULL")
                }
                writer.writeCloseBracket()
            }

            is LtExpr<*> -> {
                writer.writeOpenBracket()
                writer.writeAny(ast.left, fullFormat)
                writer.writeString(" < ")
                writer.writeAny(ast.right, fullFormat)
                writer.writeCloseBracket()
            }

            is LteExpr<*> -> {
                writer.writeOpenBracket()
                writer.writeAny(ast.left, fullFormat)
                writer.writeString(" <= ")
                writer.writeAny(ast.right, fullFormat)
                writer.writeCloseBracket()
            }

            is GtExpr<*> -> {
                writer.writeOpenBracket()
                writer.writeAny(ast.left, fullFormat)
                writer.writeString(" > ")
                writer.writeAny(ast.right, fullFormat)
                writer.writeCloseBracket()
            }

            is GteExpr<*> -> {
                writer.writeOpenBracket()
                writer.writeAny(ast.left, fullFormat)
                writer.writeString(" ≥ ")
                writer.writeAny(ast.right, fullFormat)
                writer.writeCloseBracket()
            }
        }
    }

}
