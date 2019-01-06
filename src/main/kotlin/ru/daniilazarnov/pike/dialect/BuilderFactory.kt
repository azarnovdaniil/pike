package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.dialect.builder.*

open class BuilderFactory {

    fun exprBuilder(fullFormat : Boolean): ExprBuilder = ExprBuilder(fullFormat)
    fun differenceBuilder(): DifferenceBuilder = DifferenceBuilder()
    fun intersectionBuilder(): IntersectionBuilder = IntersectionBuilder()
    fun projectionBuilder(): ProjectionBuilder = ProjectionBuilder()
    fun renameBuilder(): RenameBuilder = RenameBuilder()
    fun unionBuilder(): UnionBuilder = UnionBuilder()

}