package ru.daniilazarnov.pike.core.operation.set

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.dialect.Generator

class Union<R : Relation, P : Projection<R>>(
        val projection1: P,
        val projection2: P) : Build {

    override fun build(generator: Generator): String {
        generator.factory.unionBuilder().build(this, generator)
        return generator.toString()
    }
}
