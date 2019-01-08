package ru.daniilazarnov.pike.dialect.builder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.and
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.data.Type
import ru.daniilazarnov.pike.core.data.rangeTo
import ru.daniilazarnov.pike.core.eq
import ru.daniilazarnov.pike.core.gte
import ru.daniilazarnov.pike.core.operation.unary.Rename.Companion.rename
import ru.daniilazarnov.pike.core.operation.unary.Selection.Companion.selection
import ru.daniilazarnov.pike.dialect.MathGenerator

internal class UnaryBuilderTest {

    private object Person : Relation("Person") {
        val id = Property<Person, Type.Id>("id")
        val name = Property<Person, Type.Str>("name")
        val age = Property<Person, Type.Num>("age")
        val weight = Property<Person, Type.Num>("weight")
    }

    @Test
    fun `test selection`() {
        val expected = "σ((age ≥ 34) ∧ (name = 'Vasyan'))(Person)"
        val result = selection(Person, Person.age.gte(34).and(Person.name.eq("Vasyan"))).build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test projection`() {
        val expected = "π(age, weight)(Person)"
        val result = selection(Person)
                .projection(Person.age..Person.weight)
                .build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test selection and projection`() {
        val expected = "σ(age ≥ 34)π(age, weight)(Person)"
        val result = selection(Person, Person.age.gte(34))
                .projection(Person.age..Person.weight)
                .build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test rename`() {
        val expected = "ρ(age / old)(Person)"
        val result = rename(Person, Person.age, "old").build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test renames`() {
        val expected = "ρ(age, weight / old, fat)(Person)"
        val result = rename(Person, Person.age..Person.weight, listOf("old", "fat")).build(MathGenerator())

        assertEquals(expected, result)
    }
}