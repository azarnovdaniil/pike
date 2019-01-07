package ru.daniilazarnov.pike.core.operation.unary

import ru.daniilazarnov.pike.core.Operation
import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.operation.binary.Division
import ru.daniilazarnov.pike.core.operation.binary.Join
import ru.daniilazarnov.pike.core.operation.set.Cartesian
import ru.daniilazarnov.pike.core.operation.set.Union
import ru.daniilazarnov.pike.dialect.Generator

open class Selection<R : Relation>(
        val relation: R,
        val expr: Expr<R>?) : Operation {

    companion object {
        fun <R : Relation> selection(relation: R): Selection<R> {
            return Selection(relation, null)
        }

        fun <R : Relation> selection(relation: R, expr: Expr<R>): Selection<R> {
            return Selection(relation, expr)
        }
    }

    open fun <R2 : Relation> equiJoin(relation2: R2, expr: Expr<R2>): Join<R, R2> {
        return Join(this, relation2, Join.JoinType.EQUI, expr)
    }

    open fun <R2 : Relation> leftJoin(relation2: R2): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.LEFT, expr)
    }

    open fun <R2 : Relation> rightJoin(relation2: R2): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.RIGHT, expr)
    }

    open fun <R2 : Relation> leftAntiJoin(relation2: R2): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.LEFT_ANTI, expr)
    }

    open fun <R2 : Relation> rightAntiJoin(relation2: R2): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.RIGHT_ANTI, expr)
    }

    open fun <R2 : Relation> fullJoin(relation2: R2): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.FULL, expr)
    }

    fun <R2 : Relation> naturalJoin(relation2: R2): Join<R, R2> {
        return Join(this, relation2, Join.JoinType.NATURAL, null)
    }

    fun <P : Projection<R>> union(projection: P): Union<R, Projection<R>> {
        return projection().union(projection)
    }

    fun <S : Selection<R>> union(selection: S): Union<R, Projection<R>> {
        return union(selection.projection())
    }

    fun <P2 : Projection<*>> division(projection2: P2): Division<Projection<R>, P2> {
        return projection().division(projection2)
    }

    fun <R2 : Relation, S : Selection<R2>> division(selection: S): Division<Projection<R>, Projection<R2>> {
        return division(selection.projection())
    }

    fun <R2 : Relation> division(relation: R2): Division<Projection<R>, Projection<R2>> {
        return division(selection(relation))
    }

    fun <R2 : Relation, S : Selection<R2>> cartesian(selection: S): Cartesian<Projection<R>, Projection<R2>> {
        return cartesian(selection.projection())
    }

    fun <P2 : Projection<*>> cartesian(projection2: P2): Cartesian<Projection<R>, P2> {
        return projection().cartesian(projection2)
    }

    fun <R2 : Relation> cartesian(relation: R2): Cartesian<Projection<R>, Projection<R2>> {
        return cartesian(selection(relation))
    }

    fun projection(projection: Iterable<PropertyIterator<R>>): Projection<R> {
        return Projection(projection = projection, selection = this)
    }

    private fun projection(): Projection<R> {
        return projection(listOf())
    }

    override fun build(generator: Generator): String {
        generator.factory.projectionBuilder().build(this.projection(), generator)
        return generator.toString()
    }

}