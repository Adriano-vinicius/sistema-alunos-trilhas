package model
enum class CourseLevel(val label: String) {
    BASIC("Básico"),
    INTERMEDIATE("Intermediário"),
    ADVANCED("Avançado")
}

enum class CourseCategory(val label: String) {
    PROGRAMMING("Programação"),
    DESIGN("Design UI/UX"),
    MOBILE("Desenvolvimento Móvel")
}
class Course(
    var id: Int,
    var title: String,
    var workloadHours: Int,
    var level: CourseLevel,
    var category: CourseCategory
) {
    init {
        require(id > 0) { "O ID do curso deve ser maior que zero." }
        require(title.isNotBlank()) { "O título do curso não pode estar em branco." }
        require(workloadHours > 0) { "A carga horária deve ser maior que zero." }
    }
}