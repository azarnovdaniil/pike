package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.operation.binary.Division
import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.core.operation.set.Difference
import ru.daniilazarnov.pike.core.operation.set.Intersection
import ru.daniilazarnov.pike.core.operation.set.Union
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.core.operation.unary.Rename
import ru.daniilazarnov.pike.core.operation.unary.Selection

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