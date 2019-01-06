package ru.daniilazarnov.pike.core

import ru.daniilazarnov.pike.dialect.Generator

interface Build {

    fun build(generator: Generator): String

}