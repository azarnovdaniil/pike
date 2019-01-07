package ru.daniilazarnov.pike.core.operation.unary

import ru.daniilazarnov.pike.core.Build
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.operation.binary.Division
import ru.daniilazarnov.pike.core.operation.set.Cartesian
import ru.daniilazarnov.pike.core.operation.set.Difference
import ru.daniilazarnov.pike.core.operation.set.Intersection
import ru.daniilazarnov.pike.core.operation.set.Union
import ru.daniilazarnov.pike.dialect.Generator

class Projection<R : Relation>(
        val projection: Iterable<PropertyIterator<R>>,
        val selection: Selection<R>) : Build {

    fun <P : Projection<R>> union(projection2: P): Union<R, Projection<R>> {
        return Union(this, projection2)
    }

    fun <P : Projection<R>> intersect(projection2: P): Intersection<R, Projection<R>> {
        return Intersection(this, projection2)
    }

    fun <P : Projection<R>> diff(projection2: P): Difference<R, Projection<R>> {
        return Difference(this, projection2)
    }

    fun <P2 : Projection<*>> division(projection2: P2): Division<Projection<R>, P2> {
        return Division(this, projection2)
    }

    fun <P2 : Projection<*>> cartesian(projection2: P2): Cartesian<Projection<R>, P2> {
        return Cartesian(this, projection2)
    }

    override fun build(generator: Generator): String {
        generator.factory.projectionBuilder().build(this, generator)
        return generator.toString()
    }

}
