package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.builder.UnionModule
import ru.daniilazarnov.pike.core.data.Relation

class Union<R : Relation, P : Projection<R>>(
        val projection1: P,
        val projection2: P
) : Build {

    override fun build(): String {
        return UnionModule<R>().build(this)
    }

}
