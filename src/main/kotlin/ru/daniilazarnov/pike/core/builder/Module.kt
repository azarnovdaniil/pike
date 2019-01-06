package ru.daniilazarnov.pike.core.builder

interface Module<A : Any> {

    fun build(ast: A): String

}