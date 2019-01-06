package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.dialect.builder.*

open class MathBuilderFactory : BuilderFactory {

    override fun exprBuilder(fullFormat: Boolean): ExprBuilder = ExprBuilder(fullFormat)
    override fun differenceBuilder(): DifferenceBuilder = DifferenceBuilder
    override fun intersectionBuilder(): IntersectionBuilder = IntersectionBuilder
    override fun projectionBuilder(): ProjectionBuilder = ProjectionBuilder
    override fun renameBuilder(): RenameBuilder = RenameBuilder
    override fun unionBuilder(): UnionBuilder = UnionBuilder

}