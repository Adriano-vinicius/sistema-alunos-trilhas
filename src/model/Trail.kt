package model

class Trail(
    var id: Int,
    var name: String
) {
    var courses = ArrayList<Course>()

    init {
        require(id > 0) { "O ID da trilha deve ser maior que zero." }
        require(name.isNotBlank()) { "O nome da trilha não pode estar em branco." }
    }

    fun addCourse(course: Course): Boolean {
        val jaExiste = courses.any { it.id == course.id }

        return if (jaExiste) {
            false
        } else {
            courses.add(course)
            true
        }
    }
}