package ru.daniilazarnov.pike.query.statement

import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Projection
import ru.daniilazarnov.pike.query.Selection

class OffsetClause<R : Relation>(
        val offset: Any,
        val selection: Selection<R>,
        val groupClause: GroupClause<R>?,
        val havingClause: HavingClause<R>?) {

    inline fun projection(projection: (R) -> Iterable<PropertyIterator<R>>): Projection<R> {
        return Projection(
                projection(selection.relation),
                selection,
                this,
                groupClause,
                havingClause)
    }
}

