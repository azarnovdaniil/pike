package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Predicate
import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation

class GroupClause<T: Relation>(
        val projection: Iterable<Projection>,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?) {

    inline fun having(predicate: (T) -> Predicate): HavingClause<T> {
        return HavingClause(predicate(subject.table), subject, this, whereClause)
    }

    inline fun orderBy(order: (T) -> Iterable<Ordering>): OrderClause<T> {
        return OrderClause(order(subject.table), subject, whereClause, this, null)
    }

    inline fun limit(limit: () -> Any): LimitClause<T> {
        return LimitClause(
                limit(),
                subject,
                whereClause,
                null,
                this,
                null)
    }

    inline fun offset(offset: () -> Any): OffsetClause<T> {
        return OffsetClause(
                offset(),
                limit { "-1" },
                subject,
                whereClause,
                null,
                this,
                null)
    }

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
                projection(subject.table),
                subject,
                whereClause,
                null,
                null,
                null,
                this,
                null
        )
    }

}

class Group2Clause<T: Relation, T2: Relation>(
        val projection: Iterable<Projection>,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val where2Clause: Where2Clause<T, T2>?) {

    inline fun having(predicate: (T, T2) -> Predicate): Having2Clause<T, T2> {
        return Having2Clause(
                predicate(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                this,
                where2Clause)
    }

    inline fun orderBy(order: (T, T2) -> Iterable<Ordering>): Order2Clause<T, T2> {
        return Order2Clause(order(joinOn2Clause.subject.table, joinOn2Clause.table2), joinOn2Clause, where2Clause, this, null)
    }

    inline fun limit(limit: () -> Any): Limit2Clause<T, T2> {
        return Limit2Clause(
                limit(),
                joinOn2Clause,
                where2Clause,
                null,
                this,
                null)
    }

    inline fun offset(offset: () -> Any): Offset2Clause<T, T2> {
        return Offset2Clause(
                offset(),
                limit { "-1" },
                joinOn2Clause,
                where2Clause,
                null,
                this,
                null)
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                where2Clause,
                null,
                null,
                null,
                this,
                null
        )
    }

}
