package ru.daniilazarnov.pike.dialect

import ru.daniilazarnov.pike.core.*
import ru.daniilazarnov.pike.query.*

object MathDialect : Dialect {

    override fun <T : Table> build(statement: SelectStatement<T>): String {
        val builder = StringBuilder()

        val where = statement.whereClause
        if (where != null) {
            builder.append("σ")
            appendPredicate(builder, where.predicate, false)
        }
        val projection = statement.projection
        if(!projection.none()) {
            builder.append("π")
            builder.append("(")
            appendProjection(builder, projection, false)
            builder.append(")")
        }

        builder.append("(")
        appendTableName(builder, statement.subject.table)
        builder.append(")")




        val group = statement.groupClause
        if (group != null) {
            builder.append(" GROUP BY ")
            appendProjection(builder, group.projection, false)
        }

        val having = statement.havingClause
        if (having != null) {
            builder.append(" HAVING ")
            appendPredicate(builder, having.predicate, false)
        }

        val order = statement.orderClause
        if (order != null) {
            builder.append(" ORDER BY ")
            appendOrdering(builder, order.orderings, false)
        }

        val limit = statement.limitClause
        if (limit != null) {
            builder.append(" LIMIT ")
            builder.append(limit.limit)
        }

        val offset = statement.offsetClause
        if (offset != null) {
            builder.append(" OFFSET ")
            builder.append(offset.offset)
        }

        return builder.toString()
    }

    override fun <T : Table, T2 : Table> build(statement: Select2Statement<T, T2>): String {
        val builder = StringBuilder()

        val where = statement.where2Clause
        if (where != null) {
            builder.append("σ")
            appendPredicate(builder, where.predicate, true)
        }

        val projection = statement.projection
        if(!projection.none()) {
            builder.append("π")
            builder.append("(")
            appendProjection(builder, projection, false)
            builder.append(")")
        }

        builder.append("(")
        appendTableName(builder, statement.joinOn2Clause.subject.table)
        if (statement.joinOn2Clause.type == JoinType.NATURAL) {
            builder.append(" ⋈ ")
            appendTableName(builder, statement.joinOn2Clause.table2)
        } else {
            if (statement.joinOn2Clause.type == JoinType.OUTER) builder.append(" OUTER")
            builder.append(" JOIN ")
            appendTableName(builder, statement.joinOn2Clause.table2)
            builder.append(" ON ")
            appendPredicate(builder, statement.joinOn2Clause.condition, true)
        }
        builder.append(")")



        val group = statement.group2Clause
        if (group != null) {
            builder.append(" GROUP BY ")
            appendProjection(builder, group.projection, true)
        }

        val having = statement.having2Clause
        if (having != null) {
            builder.append(" HAVING ")
            appendPredicate(builder, having.predicate, true)
        }

        val order = statement.order2Clause
        if (order != null) {
            builder.append(" ORDER BY ")
            appendOrdering(builder, order.orderings, true)
        }

        val limit = statement.limit2Clause
        if (limit != null) {
            builder.append(" LIMIT ")
            builder.append(limit.limit)
        }

        val offset = statement.offset2Clause
        if (offset != null) {
            builder.append(" OFFSET ")
            builder.append(offset.offset)
        }

        return builder.toString()
    }

    private fun appendPredicate(builder: StringBuilder, value: Any?, fullFormat: Boolean = true) {
        when (value) {
            is Table.Column -> if (fullFormat) appendFullColumnName(builder, value) else appendShortColumnName(builder, value)

            is NotExpression -> {
                builder.append("(NOT ")
                appendPredicate(builder, value.param, fullFormat)
                builder.append(")")
            }

            is AndExpression -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" AND ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is OrExpression -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" OR ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is EqExpression -> {
                builder.append('(')
                if (value.right != null) {
                    appendPredicate(builder, value.left, fullFormat)
                    builder.append(" = ")
                    appendPredicate(builder, value.right, fullFormat)
                } else {
                    appendPredicate(builder, value.left, fullFormat)
                    builder.append(" IS NULL")
                }
                builder.append(')')
            }

            is NeExpression -> {
                builder.append('(')
                if (value.right != null) {
                    appendPredicate(builder, value.left, fullFormat)
                    builder.append(" != ")
                    appendPredicate(builder, value.right, fullFormat)
                } else {
                    appendPredicate(builder, value.left, fullFormat)
                    builder.append(" IS NOT NULL")
                }
                builder.append(')')
            }

            is LtExpression -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" < ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is LteExpression -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" <= ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is GtExpression -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" > ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            is GteExpression -> {
                builder.append('(')
                appendPredicate(builder, value.left, fullFormat)
                builder.append(" ≥ ")
                appendPredicate(builder, value.right, fullFormat)
                builder.append(')')
            }

            else -> appendValue(builder, value)
        }
    }

    private fun appendProjection(builder: StringBuilder, projection: Iterable<Projection>, fullFormat: Boolean) {
        if ("SELECT".contentEquals(builder.toString().trim()) and projection.none()) {
            builder.append("*")
        } else {
            var delim = ""
            for (proj in projection) {
                builder.append(delim)
                delim = ", "

                if (proj is Table.Column) {
                    if (fullFormat) {
                        appendFullColumnName(builder, proj)
                    } else {
                        appendShortColumnName(builder, proj)
                    }
                } else {
                    builder.append(proj)
                }
            }
        }
    }

    private fun appendOrdering(builder: StringBuilder, orderings: Iterable<Ordering>, fullFormat: Boolean) {
        var delim = ""

        for (order in orderings) {
            builder.append(delim)
            delim = ", "

            if (order.key is Table.Column) {
                if (fullFormat) {
                    appendFullColumnName(builder, order.key as Table.Column)
                } else {
                    appendShortColumnName(builder, order.key as Table.Column)
                }
            } else {
                builder.append(order.key)
            }

            builder.append(if (order.asc) " ASC" else " DESC")
        }
    }

    private fun appendTableName(builder: StringBuilder, table: Table) {
        builder.append("$table")
    }

    private fun appendShortColumnName(builder: StringBuilder, column: Table.Column) {
        builder.append("$column")
    }

    private fun appendFullColumnName(builder: StringBuilder, column: Table.Column) {
        builder.append("\"${column.table}\".\"$column\"")
    }

    private fun appendValue(builder: StringBuilder, value: Any?) {
        value?.let {
            builder.append((value as? String)?.escapedSQLString() ?: value)
        } ?: builder.append("NULL")
    }

    private fun String.escapedSQLString(): String = "\'${this.replace("'", "''")}\'"
}