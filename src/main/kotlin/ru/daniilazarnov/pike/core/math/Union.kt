package ru.daniilazarnov.pike.core.math

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.query.Projection
import ru.daniilazarnov.pike.dialect.Writer

class Union<R : Relation, P : Projection<R>>(
        val projection1: P,
        val projection2: P) : Build {

    override fun build(writer: Writer): String {
        writer.factory.unionBuilder().build(this, writer)
        return writer.toString()
    }
}
