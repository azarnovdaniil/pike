package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.data.Expr
import ru.daniilazarnov.pike.core.data.Relation

class Join<R : Relation, R2 : Relation>(
        val selection: Selection<R>,
        val relation2: R2,
        val type: JoinType,
        var condition: Expr<R2>?
) : Selection<R>(selection.relation, selection.expr) {

    enum class JoinType {
        INNER,
        OUTER,
        NATURAL
    }

    lateinit var join: Join<R, out Relation>

    override fun <R3 : Relation> join(relation2: R3, expr: Expr<R3>): Join<R, out Relation> {
        join = Join(this, relation2, JoinType.INNER, expr)

        return join
    }

//    inline fun groupBy(group: (R, R2) -> Iterable<PropertyIterator<R>>): Group2Clause<R, R2> {
//        return Group2Clause(group(selection.relation, relation2), this, null)
//    }
//
//    inline fun orderBy(order: (R, R2) -> Iterable<Ordering>): Order2Clause<R, R2> {
//        return Order2Clause(order(selection.relation, relation2), this, null, null, null)
//    }

//    inline fun limit(limit: () -> Any): Limit2Clause<R, R2> {
//        return Limit2Clause(
//                limit(),
//                this,
//                null,
//                null,
//                null,
//                null)
//    }

//    inline fun offset(offset: () -> String): Offset2Clause<R, R2> {
//        return Offset2Clause(
//                offset(),
//                limit { "-1" },
//                this,
//                null,
//                null,
//                null,
//                null)
//    }

}
