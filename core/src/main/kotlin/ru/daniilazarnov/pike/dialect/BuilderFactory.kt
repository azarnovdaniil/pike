package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.math.Difference
import ru.daniilazarnov.pike.core.math.Intersection
import ru.daniilazarnov.pike.core.math.Union
import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.core.query.Selection
import ru.daniilazarnov.pike.core.update.Rename

interface BuilderFactory {

    fun exprBuilder(fullFormat: Boolean): Builder<Expr<*>>

    fun differenceBuilder(): Builder<Difference<*, *>>
    fun intersectionBuilder(): Builder<Intersection<*, *>>
    fun projectionBuilder(): Builder<Projection<*>>
    fun selectionBuilder(): Builder<Selection<*>>
    fun joinBuilder(): Builder<Join<*, *>>
    fun renameBuilder(): Builder<Rename<*>>
    fun unionBuilder(): Builder<Union<*, *>>

}