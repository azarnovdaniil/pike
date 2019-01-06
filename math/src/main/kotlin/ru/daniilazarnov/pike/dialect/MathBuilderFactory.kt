package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.math.Difference
import ru.daniilazarnov.pike.core.math.Intersection
import ru.daniilazarnov.pike.core.math.Union
import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.core.query.Selection
import ru.daniilazarnov.pike.core.update.Rename
import ru.daniilazarnov.pike.dialect.builder.*

object MathBuilderFactory : BuilderFactory {

    override fun exprBuilder(fullFormat: Boolean): Builder<Expr<*>> = ExprBuilder(fullFormat)
    override fun differenceBuilder(): Builder<Difference<*, *>> = DifferenceBuilder
    override fun intersectionBuilder(): Builder<Intersection<*, *>> = IntersectionBuilder
    override fun projectionBuilder(): Builder<Projection<*>> = ProjectionBuilder
    override fun selectionBuilder(): Builder<Selection<*>> = SelectionBuilder
    override fun joinBuilder(): Builder<Join<*, *>> = JoinBuilder
    override fun renameBuilder(): Builder<Rename<*>> = RenameBuilder
    override fun unionBuilder(): Builder<Union<*, *>> = UnionBuilder

}