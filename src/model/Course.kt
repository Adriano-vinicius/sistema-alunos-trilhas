package model
enum class nivel(val label: String) {
    BASICO("Básico"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado")
}

enum class CATEGORIA(val label: String) {
    PROGRAMACAO("Programação"),
    DESIGN("Design"),
    MOBILE("Desenvolvimento Móvel")
}
class Course(
    var id: Int,
    var title: String,
    var horas: Int,
    var level: nivel,
    var category: CATEGORIA
) {
    init {
        require(id > 0) { "O ID do curso deve ser maior que zero." }
        require(title.isNotBlank()) { "O título do curso não pode estar em branco." }
        require(horas > 0) { "A carga horária deve ser maior que zero." }
    }
}