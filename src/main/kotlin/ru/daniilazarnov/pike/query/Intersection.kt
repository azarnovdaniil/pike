package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.builder.IntersectionBuilder
import ru.daniilazarnov.pike.core.data.Relation

class Intersection<R : Relation, P : Projection<R>>(val projection1: P, val projection2: P) : Build {

    override fun build(): String {
        return IntersectionBuilder.build(this)
    }

}
