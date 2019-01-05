package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation

class OffsetClause<T: Relation>(
        val offset: Any,
        val limit: LimitClause<T>,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?,
        val orderClause: OrderClause<T>?,
        val groupClause: GroupClause<T>?,
        val havingClause: HavingClause<T>?) {

    inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
        return SelectStatement(
                projection(subject.table),
                subject,
                whereClause,
                orderClause,
                limit,
                this,
                groupClause,
                havingClause)
    }
}

class Offset2Clause<T: Relation, T2: Relation>(
        val offset: Any,
        val limit2Clause: Limit2Clause<T, T2>,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val whereClause: Where2Clause<T, T2>?,
        val orderClause: Order2Clause<T, T2>?,
        val group2Clause: Group2Clause<T, T2>?,
        val having2Clause: Having2Clause<T, T2>?) {

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(joinOn2Clause.subject.table, joinOn2Clause.table2),
                joinOn2Clause,
                whereClause,
                orderClause,
                limit2Clause,
                this,
                group2Clause,
                having2Clause)
    }

}
