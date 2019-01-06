package ru.daniilazarnov.pike.core.math

import ru.daniilazarnov.pike.module.DifferenceBuilder
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection

class Difference<R : Relation, P : Projection<R>>(
        val projection2: P,
        val projection1: P
) {

    fun build(): String {
        return DifferenceBuilder.build(this)
    }

}