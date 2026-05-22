package model

enum class Nivel(val label: String) {
    BASICO("Básico"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado")
}

enum class Categoria(val label: String) {
    KOTLIN("Kotlin"),
    ANDROID("Android"),
    ARQUITETURA("Arquitetura"),
    TESTES("Testes"),
    DESIGN("Design")
}

data class Course(
    val id: Int,
    val title: String,
    val horas: Int,
    val level: Nivel,
    val category: Categoria
) {
    init {
        require(id > 0) { "O ID do curso deve ser maior que zero." }
        require(title.isNotBlank()) { "O título do curso não pode estar em branco." }
        require(horas > 0) { "A carga horária deve ser maior que zero." }
    }
}
