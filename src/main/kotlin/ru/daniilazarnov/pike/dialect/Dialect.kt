package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.Table
import ru.daniilazarnov.pike.query.Select2Statement
import ru.daniilazarnov.pike.query.SelectStatement

interface Dialect {

    fun <T: Table> build(statement: SelectStatement<T>): String
    fun <T: Table, T2: Table> build(statement: Select2Statement<T, T2>): String

}