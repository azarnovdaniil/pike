package ru.daniilazarnov.pike.core.operation.binary

import ru.daniilazarnov.pike.core.Operation
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.dialect.Generator

class Division<P : Projection<*>, P2 : Projection<*>>(
        val projection1: P,
        val projection2: P2
) : Operation {

    override fun build(generator: Generator): String {
        generator.factory.divisionBuilder().build(this, generator)
        return generator.toString()
    }

}