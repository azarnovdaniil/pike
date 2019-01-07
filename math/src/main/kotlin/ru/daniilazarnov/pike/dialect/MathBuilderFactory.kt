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
import ru.daniilazarnov.pike.dialect.builder.*

object MathBuilderFactory : BuilderFactory {

    override fun exprBuilder(fullFormat: Boolean): QBuilder<Expr<*>> = ExprQBuilder(fullFormat)

    override fun differenceBuilder(): QBuilder<Difference<*, *>> = DifferenceQBuilder
    override fun intersectionBuilder(): QBuilder<Intersection<*, *>> = IntersectionQBuilder
    override fun unionBuilder(): QBuilder<Union<*, *>> = UnionQBuilder

    override fun projectionBuilder(): QBuilder<Projection<*>> = ProjectionQBuilder
    override fun selectionBuilder(): QBuilder<Selection<*>> = SelectionQBuilder
    override fun renameBuilder(): QBuilder<Rename<*>> = RenameQBuilder

    override fun joinBuilder(): QBuilder<Join<*, *>> = JoinQBuilder
    override fun divisionBuilder(): QBuilder<Division<*, *>> = DivisionQBuilder

}