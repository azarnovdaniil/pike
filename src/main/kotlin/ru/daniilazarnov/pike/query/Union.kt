package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.builder.UnionBuilder
import ru.daniilazarnov.pike.core.data.Relation

class Union<T : Relation, P : Projection<T>>(val projection1: P, val projection2: P) : Build {

    override fun build(): String {
        return UnionBuilder.build(this)
    }

}
