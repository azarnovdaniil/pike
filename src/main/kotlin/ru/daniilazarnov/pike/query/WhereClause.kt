package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Predicate
import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation

class WhereClause<T : Relation>(
        val predicate: Predicate,
        val subject: Subject<T>
) {

    inline fun groupBy(group: (T) -> Iterable<Projection>): GroupClause<T> {
        return GroupClause(group(subject.table), subject, this)
    }

    inline fun orderBy(order: (T) -> Iterable<Ordering>): OrderClause<T> {
        return OrderClause(order(subject.table), subject, this, null, null)
    }

    inline fun limit(limit: () -> Any): LimitClause<T> {
        return LimitClause(
            limit(), subject, this, null, null, null
        )
    }

    inline fun offset(offset: () -> Any): OffsetClause<T> {
        return OffsetClause(
            offset(),
            limit { "-1" },
            subject,
            this,
            null,
            null,
            null
        )
    }

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
            projection(subject.table),
            subject,
            this,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun selectAll(): SelectStatement<T> {
        return SelectStatement(
            listOf(),
            subject,
            this,
            null,
            null,
            null,
            null,
            null
        )
    }

}

class Where2Clause<T : Relation, T2 : Relation>(
        val predicate: Predicate,
        val joinOn2Clause: JoinOn2Clause<T, T2>
) {

    inline fun groupBy(group: (T, T2) -> Iterable<Projection>): Group2Clause<T, T2> {
        return Group2Clause(group(joinOn2Clause.subject.table, joinOn2Clause.table2), joinOn2Clause, this)
    }

    inline fun orderBy(order: (T, T2) -> Iterable<Ordering>): Order2Clause<T, T2> {
        return Order2Clause(order(joinOn2Clause.subject.table, joinOn2Clause.table2), joinOn2Clause, this, null, null)
    }

    inline fun limit(limit: () -> Any): Limit2Clause<T, T2> {
        return Limit2Clause(
            limit(),
            joinOn2Clause,
            this,
            null,
            null,
            null
        )
    }

    inline fun offset(offset: () -> Any): Offset2Clause<T, T2> {
        return Offset2Clause(
            offset(),
            limit { "-1" },
            joinOn2Clause,
            this,
            null,
            null,
            null
        )
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
            projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
            joinOn2Clause,
            this,
            null,
            null,
            null,
            null,
            null
        )
    }
}
