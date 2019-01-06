package ru.daniilazarnov.pike.dialect.builder

import ru.daniilazarnov.pike.core.*
import ru.daniilazarnov.pike.core.data.*
import ru.daniilazarnov.pike.dialect.Builder
import ru.daniilazarnov.pike.dialect.Writer

class ExprBuilder(private val fullFormat: Boolean = true) : Builder<Expr<*>> {

    override fun build(ast: Expr<*>, writer: Writer) {
        when (ast) {

            is NotExpr<*> -> {
                writer.writeString("(NOT ")
                appendPredicate(ast.param, writer)
                writer.writeCloseBracket()
            }

            is AndExpr<*> -> {
                writer.writeOpenBracket()
                appendPredicate(ast.left, writer)
                writer.writeString(" AND ")
                appendPredicate(ast.right, writer)
                writer.writeCloseBracket()
            }

            is OrExpr<*> -> {
                writer.writeOpenBracket()
                appendPredicate(ast.left, writer)
                writer.writeString(" OR ")
                appendPredicate(ast.right, writer)
                writer.writeCloseBracket()
            }

            is EqExpr<*> -> {
                writer.writeOpenBracket()
                if (ast.right != null) {
                    appendPredicate(ast.left, writer)
                    writer.writeString(" = ")
                    appendPredicate(ast.right, writer)
                } else {
                    appendPredicate(ast.left, writer)
                    writer.writeString(" IS NULL")
                }
                writer.writeCloseBracket()
            }

            is LtExpr<*> -> {
                writer.writeOpenBracket()
                appendPredicate(ast.left, writer)
                writer.writeString(" < ")
                appendPredicate(ast.right, writer)
                writer.writeCloseBracket()
            }

            is LteExpr<*> -> {
                writer.writeOpenBracket()
                appendPredicate(ast.left, writer)
                writer.writeString(" <= ")
                appendPredicate(ast.right, writer)
                writer.writeCloseBracket()
            }

            is GtExpr<*> -> {
                writer.writeOpenBracket()
                appendPredicate(ast.left, writer)
                writer.writeString(" > ")
                appendPredicate(ast.right, writer)
                writer.writeCloseBracket()
            }

            is GteExpr<*> -> {
                writer.writeOpenBracket()
                appendPredicate(ast.left, writer)
                writer.writeString(" ≥ ")
                appendPredicate(ast.right, writer)
                writer.writeCloseBracket()
            }
        }
    }

    private fun appendPredicate(ast: Any?, writer: Writer) {
        when (ast) {
            is Expr<*> -> build(ast, writer)

            is Relation.Property<*, *> -> {
                if (fullFormat) {
                    writer.appendFullPropertyName(ast)
                } else {
                    writer.appendShortPropertyName(ast)
                }
            }

            else -> writer.appendValue(ast)
        }
    }

}
