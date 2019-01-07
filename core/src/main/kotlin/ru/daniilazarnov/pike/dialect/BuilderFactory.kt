package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.operation.binary.Division
import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.core.operation.set.Cartesian
import ru.daniilazarnov.pike.core.operation.set.Difference
import ru.daniilazarnov.pike.core.operation.set.Intersection
import ru.daniilazarnov.pike.core.operation.set.Union
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.core.operation.unary.Rename
import ru.daniilazarnov.pike.core.operation.unary.Selection

interface BuilderFactory {

    fun exprBuilder(fullFormat: Boolean): OperationBuilder<Expr<*>>

    fun differenceBuilder(): OperationBuilder<Difference<*, *>>
    fun intersectionBuilder(): OperationBuilder<Intersection<*, *>>
    fun unionBuilder(): OperationBuilder<Union<*, *>>
    fun cartesianBuilder(): OperationBuilder<Cartesian<*, *>>

    fun projectionBuilder(): OperationBuilder<Projection<*>>
    fun selectionBuilder(): OperationBuilder<Selection<*>>
    fun renameBuilder(): OperationBuilder<Rename<*>>

    fun joinBuilder(): OperationBuilder<Join<*, *>>
    fun divisionBuilder(): OperationBuilder<Division<*, *>>

}