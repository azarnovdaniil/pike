package ru.daniilazarnov.pike.dialect.builder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.data.*
import ru.daniilazarnov.pike.core.eq
import ru.daniilazarnov.pike.core.gte
import ru.daniilazarnov.pike.core.operation.unary.Selection.Companion.selection
import ru.daniilazarnov.pike.dialect.MathGenerator

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
        val result = selection(Person, Person.age.gte(34)).build(MathGenerator())

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
    fun `test natural join`() {
        val expected = "(Person ⋈ Address)"
        val result = selection(Person).naturalJoin(Address).build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test join`() {
        val expected = "(Person ⋈(Person.address = Address.id) Address)"
        val result = selection(Person)
                .equiJoin(Address, Person.address.eq(Address.id))
                .build(MathGenerator())


        assertEquals(expected, result)
    }

    @Test
    fun `test double join`() {
        val expected = "(Person ⋈(Person.address = Address.id) Address) ⋈(Person.phone = Contact.id) Contact)"
        val result = selection(Person)
                .equiJoin(Contact, Person.phone.eq(Contact.id))
                .equiJoin(Address, Person.address.eq(Address.id))
                .build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test union`() {
        val expected = "((Person) ∪ (Person))"
        val result = selection(Person).union(selection(Person)).build(MathGenerator())

        assertEquals(expected, result)
    }

}
