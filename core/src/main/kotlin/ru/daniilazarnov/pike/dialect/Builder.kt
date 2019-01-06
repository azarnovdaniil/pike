package ru.daniilazarnov.pike.dialect

interface Builder<A : Any> {

    fun build(ast: A, generator: Generator)

}