package ru.daniilazarnov.pike.dialect

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.data.*
import ru.daniilazarnov.pike.query.Selection.Companion.selection

class ProjectionBuilderTest {
    private object Person : Relation("Person") {
        val id = Property<Person, Type.Id>("id")
        val name = Property<Person, Type.Str>("name")
        val age = Property<Person, Type.Num>("age")
        val weight = Property<Person, Type.Num>("weight")
        val address = Property<Person, Type.Id>("address")
        val phone = Property<Person, Type.Id>("phone")
    }

    private object Address : Relation("Address") {
        val id = Property<Person, Type.Id>("id")
        val city = Property<Person, Type.Str>("city")
    }

    private object Contact : Relation("Contact") {
        val id = Property<Person, Type.Id>("id")
        val phone = Property<Person, Type.Str>("phone")
    }

    @Test
    fun `test selection`() {
        val expected = "σ(age ≥ 34)(Person)"
        val result = selection(Person, Person.age.gte(34)).build()

        assertEquals(expected, result)
    }

    @Test
    fun `test projection`() {
        val expected = "π(age, weight)(Person)"
        val result = selection(Person)
                .projection(Person.age..Person.weight)
                .build()

        assertEquals(expected, result)
    }

    @Test
    fun `test selection and projection`() {
        val expected = "σ(age ≥ 34)π(age, weight)(Person)"
        val result = selection(Person, Person.age.gte(34))
                .projection(Person.age..Person.weight)
                .build()

        assertEquals(expected, result)
    }

    @Test
    fun `test natural join`() {
        val expected = "(Person ⋈ Address)"
        val result = selection(Person).naturalJoin(Address).build()

        assertEquals(expected, result)
    }

    @Test
    fun `test join`() {
        val expected = "(Person ⋈(Person.id = Address.id) Address)"
        val result = selection(Person)
                .join(Address, Person.id.eq(Address.id))
                .build()

        assertEquals(expected, result)
    }

    @Test
    fun `test double join`() {
        val expected = "(Person ⋈(Person.id = Address.id) Address)"
        val result = selection(Person)
                .join(Address, Person.id.eq(Address.id))
                .join(Contact, Person.id.eq(Contact.id))
                .build()

        assertEquals(expected, result)
    }

    @Test
    fun `test union`() {
        val expected = "((Person) ∪ (Person))"
        val result = selection(Person)
                .union(selection(Person))
                .build()

        assertEquals(expected, result)
    }

}
