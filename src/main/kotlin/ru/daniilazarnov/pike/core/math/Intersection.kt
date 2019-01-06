package ru.daniilazarnov.pike.core.math

import ru.daniilazarnov.pike.module.IntersectionBuilder
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection

class Intersection<R : Relation, P : Projection<R>>(
        val projection1: P,
        val projection2: P
) {

    fun build(): String {
        return IntersectionBuilder.build(this)
    }

}
