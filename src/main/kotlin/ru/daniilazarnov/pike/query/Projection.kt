package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.builder.SelectionBuilder
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.statement.GroupClause
import ru.daniilazarnov.pike.query.statement.HavingClause
import ru.daniilazarnov.pike.query.statement.OffsetClause

class Projection<R : Relation>(
        val projection: Iterable<PropertyIterator<R>>,
        val selection: Selection<R>,
        val offsetClause: OffsetClause<R>? = null,
        val groupClause: GroupClause<R>? = null,
        val havingClause: HavingClause<R>? = null) : Build {

    infix fun <P : Projection<R>> union(projection2: P): Union<R, Projection<R>> {
        return Union(this, projection2)
    }

    infix fun <P : Projection<R>> intersect(projection2: P): Intersection<R, Projection<R>> {
        return Intersection(this, projection2)
    }

    infix fun <P : Projection<R>> diff(projection2: P): Difference<R, Projection<R>> {
        return Difference(this, projection2)
    }

    override fun build(): String {
        return SelectionBuilder.build(this)
    }

}
