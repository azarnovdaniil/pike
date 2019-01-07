package ru.daniilazarnov.pike.core.query

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.unary.Projection
import ru.daniilazarnov.pike.dialect.Generator

class Division<P : Projection<*>, P2 : Projection<*>>(
        val projection1: P,
        val projection2: P2
) : Build {

    override fun build(generator: Generator): String {
        generator.factory.divisionBuilder().build(this, generator)
        return generator.toString()
    }

}