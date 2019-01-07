package ru.daniilazarnov.pike.core.query

import ru.daniilazarnov.pike.core.Expr
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.unary.Selection

class Join<R : Relation, R2 : Relation>(
        val selection: Selection<R>,
        val relation2: R2,
        val type: JoinType,
        var condition: Expr<R2>?
) : Selection<R>(selection.relation, selection.expr) {

    enum class JoinType {
        NATURAL,
        EQUI,
        LEFT,
        RIGHT,
        ANTI
    }

    var join: Join<R, out Relation>? = null

    override fun <R3 : Relation> equiJoin(relation2: R3, expr: Expr<R3>): Join<R, R3> {
        val join = Join(this, relation2, JoinType.EQUI, expr)
        this.join = join

        return join
    }

}
