package ru.daniilazarnov.pike.dialect

interface QBuilder<A : Any> {

    fun build(ast: A, generator: Generator)

}