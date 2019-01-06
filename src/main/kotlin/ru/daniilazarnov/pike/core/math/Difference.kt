package ru.daniilazarnov.pike.core.math

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.dialect.BuilderFactory
import ru.daniilazarnov.pike.dialect.Writer

class Difference<R : Relation, P : Projection<R>>(
        val projection2: P,
        val projection1: P) : Build {

    override fun build(writer: Writer): String {
        writer.factory.differenceBuilder().build(this, writer)
        return writer.toString()
    }
}