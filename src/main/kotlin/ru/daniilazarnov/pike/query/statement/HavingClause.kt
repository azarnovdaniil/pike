package ru.daniilazarnov.pike.query.statement

import ru.daniilazarnov.pike.core.data.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Projection
import ru.daniilazarnov.pike.query.Selection

class HavingClause<R: Relation>(
        val expr: Expr<R>,
        val groupClause: GroupClause<R>,
        val selection: Selection<R>) {

    inline fun offset(offset: () -> Any): OffsetClause<R> {
        return OffsetClause(
                offset(),
                selection,
                groupClause,
                this)
    }

    inline fun projection(projection: (R) -> Iterable<PropertyIterator<R>>): Projection<R> {
        return Projection(
                projection(selection.relation),
                selection,
                null,
                groupClause,
                this)
    }

}
