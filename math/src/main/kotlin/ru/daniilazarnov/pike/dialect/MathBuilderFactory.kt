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
import ru.daniilazarnov.pike.dialect.builder.*

object MathBuilderFactory : BuilderFactory {

    override fun exprBuilder(fullFormat: Boolean): QBuilder<Expr<*>> = ExprQBuilder(fullFormat)

    override fun differenceBuilder(): QBuilder<Difference<*, *>> = DifferenceQBuilder
    override fun intersectionBuilder(): QBuilder<Intersection<*, *>> = IntersectionQBuilder
    override fun unionBuilder(): QBuilder<Union<*, *>> = UnionQBuilder
    override fun cartesianBuilder(): QBuilder<Cartesian<*, *>> = CartesianQBuilder

    override fun projectionBuilder(): QBuilder<Projection<*>> = ProjectionQBuilder
    override fun selectionBuilder(): QBuilder<Selection<*>> = SelectionQBuilder
    override fun renameBuilder(): QBuilder<Rename<*>> = RenameQBuilder

    override fun joinBuilder(): QBuilder<Join<*, *>> = JoinQBuilder
    override fun divisionBuilder(): QBuilder<Division<*, *>> = DivisionQBuilder

}