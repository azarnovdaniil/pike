package ru.daniilazarnov.pike.core.operation.set

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.operation.unary.Projection
import ru.daniilazarnov.pike.dialect.Generator

class Cartesian<P : Projection<*>, P2 : Projection<*>>(
        val projection1: P,
        val projection2: P2) : Build {

    override fun build(generator: Generator): String {
        generator.factory.cartesianBuilder().build(this, generator)
        return generator.toString()
    }
}