package ru.daniilazarnov.pike.query.statement

import ru.daniilazarnov.pike.core.data.Expr
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Selection

class GroupClause<R : Relation>(
        val projection: Iterable<PropertyIterator<R>>,
        val selection: Selection<R>) {

    inline fun having(expr: (R) -> Expr<R>): HavingClause<R> {
        return HavingClause(expr(selection.relation), this, selection)
    }

    inline fun offset(offset: () -> Any): OffsetClause<R> {
        return OffsetClause(
                offset(),
                selection,
                this,
                null)
    }

}
