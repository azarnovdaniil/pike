package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation

class OrderClause<T: Relation>(
        val orderings: Iterable<Ordering>,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?,
        val groupClause: GroupClause<T>?,
        val havingClause: HavingClause<T>?) {

    inline fun limit(limit: () -> Any): LimitClause<T> {
        return LimitClause(
                limit(),
                subject,
                whereClause,
                this,
                groupClause,
                havingClause)
    }

    inline fun offset(offset: () -> Any): OffsetClause<T> {
        return OffsetClause(
                offset(),
                limit { "-1" },
                subject,
                whereClause,
                this,
                groupClause,
                havingClause)
    }

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
                projection(subject.table),
                subject,
                whereClause,
                this,
                null,
                null,
                groupClause,
                havingClause)
    }

}

class Order2Clause<T: Relation, T2: Relation>(
        val orderings: Iterable<Ordering>,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val where2Clause: Where2Clause<T, T2>?,
        val group2Clause: Group2Clause<T, T2>?,
        val having2Clause: Having2Clause<T, T2>?) {

    inline fun limit(limit: () -> Any): Limit2Clause<T, T2> {
        return Limit2Clause(
                limit(),
                joinOn2Clause,
                where2Clause,
                this,
                group2Clause,
                having2Clause)
    }

    inline fun offset(offset: () -> Any): Offset2Clause<T, T2> {
        return Offset2Clause(
                offset(),
                limit { "-1" },
                joinOn2Clause,
                where2Clause,
                this,
                group2Clause,
                having2Clause)
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                where2Clause,
                this,
                null,
                null,
                group2Clause,
                having2Clause)
    }

}
