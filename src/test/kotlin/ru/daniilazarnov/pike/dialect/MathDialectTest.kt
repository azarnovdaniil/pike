package ru.daniilazarnov.pike.dialect

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.*

class MathDialectTest {
    private object PersonTable : Table("Person") {
        val id = Column("id")
        val name = Column("name")
        val age = Column("age")
        val weight = Column("weight")
        val address = Column("address")
    }

    private object AddressTable : Table("Address") {
        val id = Column("id")
        val city = Column("city")
        val street = Column("street")
        val house = Column("house")
    }

    @Test
    fun `test selection`() {
        val expected = "σ(age ≥ 34)(Person)"
        val result = from(PersonTable).where { (PersonTable.age gte 34) }.selectAll().toString(MathDialect)

        assertEquals(expected, result)
    }

    @Test
    fun `test projection`() {
        val expected = "π(age, weight)(Person)"
        val result = from(PersonTable).select { PersonTable.age..PersonTable.weight }.toString(MathDialect)

        assertEquals(expected, result)
    }

    @Test
    fun `test selection and projection`() {
        val expected = "σ(age ≥ 34)π(age, weight)(Person)"
        val result = from(PersonTable).where { (PersonTable.age gte 34) }.select { PersonTable.age..PersonTable.weight }.toString(MathDialect)

        assertEquals(expected, result)
    }

    @Test
    fun `natural join`() {
        val expected = "(Person ⋈ Address)"
        val result = from(PersonTable).naturalJoin(AddressTable).selectAll().toString(MathDialect)
        assertEquals(expected, result)
    }

    @Test
    fun `union`() {
        val expected = "(Person ∪ Address)"
        val result = from(PersonTable).naturalJoin(AddressTable).selectAll().toString(MathDialect)
        assertEquals(expected, result)
    }

}