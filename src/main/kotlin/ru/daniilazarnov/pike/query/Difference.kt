package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.builder.DifferenceBuilder
import ru.daniilazarnov.pike.core.data.Relation

class Difference<R : Relation, P : Projection<R>>(
        val projection2: P,
        val projection1: P
) : Build {

    override fun build(): String {
        return DifferenceBuilder.build(this)
    }

}