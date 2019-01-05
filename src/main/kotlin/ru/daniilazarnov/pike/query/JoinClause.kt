package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.Predicate
import ru.daniilazarnov.pike.core.Subject
import ru.daniilazarnov.pike.core.Relation

class Join2Clause<T: Relation, T2: Relation>(
        val subject: Subject<T>,
        val table2: T2,
        val type: JoinType = JoinType.INNER
) {

    inline fun on(condition: (T, T2) -> Predicate): JoinOn2Clause<T, T2> {
        return JoinOn2Clause(
                subject,
                table2,
                type,
                condition(subject.table, table2)
        )
    }

}

class JoinOn2Clause<T: Relation, T2: Relation>(
        val subject: Subject<T>,
        val table2: T2,
        val type: JoinType,
        val condition: Predicate
) {

    inline fun where(predicate: (T, T2) -> Predicate): Where2Clause<T, T2> {
        return Where2Clause(predicate(subject.table, table2), this)
    }

    inline fun groupBy(group: (T, T2) -> Iterable<Projection>): Group2Clause<T, T2> {
        return Group2Clause(group(subject.table, table2), this, null)
    }

    inline fun orderBy(order: (T, T2) -> Iterable<Ordering>): Order2Clause<T, T2> {
        return Order2Clause(order(subject.table, table2), this, null, null, null)
    }

    inline fun limit(limit: () -> Any): Limit2Clause<T, T2> {
        return Limit2Clause(
                limit(),
                this,
                null,
                null,
                null,
                null)
    }

    inline fun offset(offset: () -> String): Offset2Clause<T, T2> {
        return Offset2Clause(
                offset(),
                limit { "-1" },
                this,
                null,
                null,
                null,
                null)
    }

    fun selectAll(): Select2Statement<T, T2> {
        return Select2Statement(
                listOf(),
                this,
                null,
                null,
                null,
                null,
                null,
                null
        )
    }

    inline fun select(projection: (T, T2) -> Iterable<Projection>): Select2Statement<T, T2> {
        return Select2Statement(
                projection(subject.table, table2),
                this,
                null,
                null,
                null,
                null,
                null,
                null)
    }

}

enum class JoinType {
    INNER,
    OUTER,
    NATURAL
}