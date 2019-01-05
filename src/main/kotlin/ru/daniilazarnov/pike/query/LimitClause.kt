package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation

class LimitClause<T : Relation>(
        val limit: Any,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?,
        val orderClause: OrderClause<T>?,
        val groupClause: GroupClause<T>?,
        val havingClause: HavingClause<T>?) {

    inline fun offset(offset: () -> Any): OffsetClause<T> {
        return OffsetClause(
                offset(),
                this,
                subject,
                whereClause,
                orderClause,
                groupClause,
                havingClause)
    }

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
                projection(subject.table),
                subject,
                whereClause,
                orderClause,
                this,
                null,
                groupClause,
                havingClause)
    }
}

class Limit2Clause<T : Relation, T2 : Relation>(
        val limit: Any,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val where2Clause: Where2Clause<T, T2>?,
        val order2Clause: Order2Clause<T, T2>?,
        val group2Clause: Group2Clause<T, T2>?,
        val having2Clause: Having2Clause<T, T2>?) {

    inline fun offset(offset: () -> Any): Offset2Clause<T, T2> {
        return Offset2Clause(
                offset(),
                this,
                joinOn2Clause,
                where2Clause,
                order2Clause,
                group2Clause,
                having2Clause)
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                where2Clause,
                order2Clause,
                this,
                null,
                group2Clause,
                having2Clause)
    }

}
