package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.query.Division
import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.set.Difference
import ru.daniilazarnov.pike.core.set.Intersection
import ru.daniilazarnov.pike.core.set.Union
import ru.daniilazarnov.pike.core.unary.Projection
import ru.daniilazarnov.pike.core.unary.Rename
import ru.daniilazarnov.pike.core.unary.Selection

interface BuilderFactory {

    fun exprBuilder(fullFormat: Boolean): QBuilder<Expr<*>>

    fun differenceBuilder(): QBuilder<Difference<*, *>>
    fun intersectionBuilder(): QBuilder<Intersection<*, *>>
    fun unionBuilder(): QBuilder<Union<*, *>>

    fun projectionBuilder(): QBuilder<Projection<*>>
    fun selectionBuilder(): QBuilder<Selection<*>>
    fun renameBuilder(): QBuilder<Rename<*>>

    fun joinBuilder(): QBuilder<Join<*, *>>
    fun divisionBuilder(): QBuilder<Division<*, *>>

}