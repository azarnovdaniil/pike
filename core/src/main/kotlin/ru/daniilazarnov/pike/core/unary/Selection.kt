package ru.daniilazarnov.pike.core.unary

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Division
import ru.daniilazarnov.pike.core.query.Join
import ru.daniilazarnov.pike.core.set.Union
import ru.daniilazarnov.pike.dialect.Generator

open class Selection<R : Relation>(
        val relation: R,
        val expr: Expr<R>?) : Build {

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

    open fun <R2 : Relation> antiJoin(relation2: R2): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.ANTI, expr)
    }

    fun <R2 : Relation> naturalJoin(relation2: R2): Join<R, R2> {
        return Join(this, relation2, Join.JoinType.NATURAL, null)
    }

    fun <P : Projection<R>> union(projection: P): Union<R, Projection<R>> {
        return Union(Projection(projection = listOf(), selection = this), projection)
    }

    fun <P2 : Projection<*>> division(projection2: P2): Division<Projection<R>, P2> {
        return projection(listOf()).division(projection2)
    }

    fun <R2 : Relation, S : Selection<R2>> division(selection: S): Division<Projection<R>, Projection<R2>> {
        return division(selection.projection(listOf()))
    }

    fun <R2 : Relation> division(relation: R2): Division<Projection<R>, Projection<R2>> {
        return division(selection(relation))
    }

    fun <S : Selection<R>> union(selection: S): Union<R, Projection<R>> {
        return union(Projection(projection = listOf(), selection = selection))
    }

    fun projection(projection: Iterable<PropertyIterator<R>>): Projection<R> {
        return Projection(projection = projection, selection = this)
    }

    override fun build(generator: Generator): String {
        generator.factory.projectionBuilder().build(this.projection(listOf()), generator)
        return generator.toString()
    }

}