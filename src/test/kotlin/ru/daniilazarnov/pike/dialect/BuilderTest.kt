package ru.daniilazarnov.pike.dialect

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.*

class BuilderTest {
    private object PersonRelation : Relation("Person") {
        val id = Property("id")
        val name = Property("name")
        val age = Property("age")
        val weight = Property("weight")
        val address = Property("address")
    }

    private object AddressRelation : Relation("Address") {
        val id = Property("id")
        val city = Property("city")
        val street = Property("street")
        val house = Property("house")
    }

    @Test
    fun `test selection`() {
        val expected = "σ(age ≥ 34)(Person)"
        val result = from(PersonRelation).where { (PersonRelation.age gte 34) }.selectAll().toString()

        assertEquals(expected, result)
    }

    @Test
    fun `test projection`() {
        val expected = "π(age, weight)(Person)"
        val result = from(PersonRelation).select { PersonRelation.age..PersonRelation.weight }.toString()

        assertEquals(expected, result)
    }

    @Test
    fun `test selection and projection`() {
        val expected = "σ(age ≥ 34)π(age, weight)(Person)"
        val result = from(PersonRelation).where { (PersonRelation.age gte 34) }.select { PersonRelation.age..PersonRelation.weight }.toString()

        assertEquals(expected, result)
    }

    @Test
    fun `natural join`() {
        val expected = "(Person ⋈ Address)"
        val result = from(PersonRelation)
                .naturalJoin(AddressRelation)
                .selectAll()
                .toString()
        assertEquals(expected, result)
    }

    @Test
    fun `join`() {
        val expected = "(Person ⋈(Address.id = Person.address) Address)"
        val result = from(PersonRelation)
                .join(AddressRelation)
                .on { e, o -> o.id eq e.address }
                .selectAll()
                .toString()

        assertEquals(expected, result)
    }

}