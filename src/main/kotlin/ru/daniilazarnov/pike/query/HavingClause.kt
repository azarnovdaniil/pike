package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Predicate
import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Table

class HavingClause<T: Table>(
        val predicate: Predicate,
        val subject: Subject<T>,
        val groupClause: GroupClause<T>,
        val whereClause: WhereClause<T>?) {

    inline fun orderBy(order: (T) -> Iterable<Ordering>): OrderClause<T> {
        return OrderClause(order(subject.table), subject, whereClause, groupClause, this)
    }

    inline fun limit(limit: () -> Any): LimitClause<T> {
        return LimitClause(
                limit(),
                subject,
                whereClause,
                null,
                groupClause,
                this)
    }

    inline fun offset(offset: () -> Any): OffsetClause<T> {
        return OffsetClause(
                offset(),
                limit { "-1" },
                subject,
                whereClause,
                null,
                groupClause,
                this)
    }

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
                projection(subject.table),
                subject,
                whereClause,
                null,
                null,
                null,
                groupClause,
                this)
    }

}

class Having2Clause<T: Table, T2: Table>(
        val predicate: Predicate,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val group2Clause: Group2Clause<T, T2>,
        val where2Clause: Where2Clause<T, T2>?) {

    inline fun orderBy(order: (T, T2) -> Iterable<Ordering>): Order2Clause<T, T2> {
        return Order2Clause(order(joinOn2Clause.subject.table, joinOn2Clause.table2), joinOn2Clause, where2Clause, group2Clause, this)
    }

    inline fun limit(limit: () -> Any): Limit2Clause<T, T2> {
        return Limit2Clause(
                limit(),
                joinOn2Clause,
                where2Clause,
                null,
                group2Clause,
                this)
    }

    inline fun offset(offset: () -> Any): Offset2Clause<T, T2> {
        return Offset2Clause(
                offset(),
                limit { "-1" },
                joinOn2Clause,
                where2Clause,
                null,
                group2Clause,
                this)
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                where2Clause,
                null,
                null,
                null,
                group2Clause,
                this
        )
    }

}
