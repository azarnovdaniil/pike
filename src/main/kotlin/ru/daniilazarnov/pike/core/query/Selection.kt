package ru.daniilazarnov.pike.core.query

import ru.daniilazarnov.pike.core.data.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.math.Union

open class Selection<R : Relation>(
        val relation: R,
        val expr: Expr<R>?
) {

    companion object {
        fun <R : Relation> selection(relation: R): Selection<R> {
            return Selection(relation, null)
        }

        fun <R : Relation> selection(relation: R, expr: Expr<R>): Selection<R> {
            return Selection(relation, expr)
        }
    }

    open fun <R2 : Relation> join(relation2: R2, expr: Expr<R2>): Join<R, out Relation> {
        return Join(this, relation2, Join.JoinType.INNER, expr)
    }

    fun <R2 : Relation> naturalJoin(relation2: R2): Join<R, R2> {
        return Join(this, relation2, Join.JoinType.NATURAL, null)
    }

    infix fun <P : Projection<R>> union(projection: P): Union<R, Projection<R>> {
        return Union(Projection(projection = listOf(), selection = this), projection)
    }

    infix fun <S : Selection<R>> union(selection: S): Union<R, Projection<R>> {
        return union(Projection(projection = listOf(), selection = selection))
    }

    fun projection(projection: Iterable<PropertyIterator<R>>): Projection<R> {
        return Projection(projection = projection, selection = this)
    }

    fun build(): String {
        return Projection(projection = listOf(), selection = this).build()
    }

}