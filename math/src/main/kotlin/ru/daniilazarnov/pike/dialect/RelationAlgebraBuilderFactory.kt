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

object RelationAlgebraBuilderFactory : BuilderFactory {

    override fun exprBuilder(fullFormat: Boolean): OperationBuilder<Expr<*>> = ExprBuilder(fullFormat)

    override fun differenceBuilder(): OperationBuilder<Difference<*, *>> = DifferenceBuilder
    override fun intersectionBuilder(): OperationBuilder<Intersection<*, *>> = IntersectionBuilder
    override fun unionBuilder(): OperationBuilder<Union<*, *>> = UnionBuilder
    override fun cartesianBuilder(): OperationBuilder<Cartesian<*, *>> = CartesianBuilder

    override fun projectionBuilder(): OperationBuilder<Projection<*>> = ProjectionBuilder
    override fun selectionBuilder(): OperationBuilder<Selection<*>> = SelectionBuilder
    override fun renameBuilder(): OperationBuilder<Rename<*>> = RenameBuilder

    override fun joinBuilder(): OperationBuilder<Join<*, *>> = JoinBuilder
    override fun divisionBuilder(): OperationBuilder<Division<*, *>> = DivisionBuilder

}