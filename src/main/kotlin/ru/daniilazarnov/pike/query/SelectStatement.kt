package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation
import ru.daniilazarnov.pike.core.Builder

class SelectStatement<T: Relation>(
        val projection: Iterable<Projection>,
        val subject: Subject<T>,
        val whereClause: WhereClause<T>?,
        val orderClause: OrderClause<T>?,
        val limitClause: LimitClause<T>?,
        val offsetClause: OffsetClause<T>?,
        val groupClause: GroupClause<T>?,
        val havingClause: HavingClause<T>?) {

    override fun toString(): String {
        return Builder.build(this)
    }
}

class Select2Statement<T: Relation, T2: Relation>(
        val projection: Iterable<Projection>,
        val joinOn2Clause: JoinOn2Clause<T, T2>,
        val where2Clause: Where2Clause<T, T2>?,
        val order2Clause: Order2Clause<T, T2>?,
        val limit2Clause: Limit2Clause<T, T2>?,
        val offset2Clause: Offset2Clause<T, T2>?,
        val group2Clause: Group2Clause<T, T2>?,
        val having2Clause: Having2Clause<T, T2>?) {

    override fun toString(): String {
        return Builder.build(this)
    }
}
