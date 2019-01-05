package ru.daniilazarnov.pike.core

import ru.daniilazarnov.pike.query.*

open class Subject<T: Relation> private constructor(val table: T) {

    override fun toString(): String {
        return table.toString()
    }

    class From<T: Relation>(table: T) : Subject<T>(table) {
        fun <T2: Relation> union(table2: T2): Join2Clause<T, T2> {
            return Join2Clause(this, table2)
        }

        fun <T2: Relation> join(table2: T2): Join2Clause<T, T2> {
            return Join2Clause(this, table2)
        }

        fun <T2: Relation> naturalJoin(table2: T2): JoinOn2Clause<T, T2> {
            return JoinOn2Clause(
                    this,
                    table2,
                    JoinType.NATURAL,
                    irrelevant()
            )
        }

        fun <T2: Relation> outerJoin(table2: T2): Join2Clause<T, T2> {
            return Join2Clause(this, table2, JoinType.OUTER)
        }

        inline fun where(predicate: (T) -> Predicate): WhereClause<T> {
            return WhereClause(predicate(table), this)
        }

        inline fun groupBy(projection: (T) -> Iterable<Projection>): GroupClause<T> {
            return GroupClause(projection(table), this, null)
        }

        inline fun orderBy(order: (T) -> Iterable<Ordering>): OrderClause<T> {
            return OrderClause(order(table), this, null, null, null)
        }

        inline fun limit(limit: () -> String): LimitClause<T> {
            return LimitClause(
                    limit(),
                    this,
                    null,
                    null,
                    null,
                    null)
        }

        inline fun offset(offset: () -> String): OffsetClause<T> {
            return OffsetClause(
                    offset(),
                    limit { "-1" },
                    this,
                    null,
                    null,
                    null,
                    null)
        }

        inline fun select(projection: (T) -> Iterable<Projection>): SelectStatement<T> {
            return SelectStatement(
                    projection(table),
                    this,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null)
        }

    }
}

fun <T: Relation> from(table: T): Subject.From<T> {
    return Subject.From(table)
}