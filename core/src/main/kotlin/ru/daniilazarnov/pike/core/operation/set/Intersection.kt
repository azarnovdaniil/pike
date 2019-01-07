package ru.daniilazarnov.pike.core.operation.set

import ru.daniilazarnov.pike.core.Operation
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.dialect.Generator

class Intersection<R : Relation, P : Projection<R>>(
        val projection1: P,
        val projection2: P) : Operation {

    override fun build(generator: Generator): String {
        generator.factory.intersectionBuilder().build(this, generator)
        return generator.toString()
    }
}
