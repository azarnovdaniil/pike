package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.data.Relation

open class MathGenerator : Generator() {

    override val factory: BuilderFactory = RelationAlgebraBuilderFactory

    override fun writeAny(any: Any?, fullFormat: Boolean) {
        when (any) {
            is Expr<*> -> writeExpr(any, fullFormat)

            is Relation.Property<*, *> -> writeProperty(any, fullFormat)

            else -> {
                any?.let { builder.append((any as? String)?.asString() ?: any) } ?: builder.append("ω")
            }
        }
    }

}