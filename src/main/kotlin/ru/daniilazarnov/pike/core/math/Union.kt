package ru.daniilazarnov.pike.core.math

import ru.daniilazarnov.pike.module.UnionModule
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection

class Union<R : Relation, P : Projection<R>>(
        val projection1: P,
        val projection2: P
) {

    fun build(): String {
        return UnionModule<R>().build(this)
    }

}
