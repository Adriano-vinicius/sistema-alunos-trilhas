package model

data class Student(
    val id: Int,
    val name: String,
    val email: String,
    val trail: Trail? = null
)