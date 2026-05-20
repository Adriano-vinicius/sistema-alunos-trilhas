package model

class Trail(
    var id: Int,
    var name: String
) {
    private val _courses = ArrayList<Course>()
    val courses: List<Course> get() = _courses

    init {
        require(id > 0) { "O ID da trilha deve ser maior que zero." }
        require(name.isNotBlank()) { "O nome da trilha não pode estar em branco." }
    }
    fun addCourse(course: Course): Boolean {
        val jaExiste = _courses.any { it.id == course.id }
        return if (jaExiste) {
            false
        } else {
            _courses.add(course)
            true
        }
    }
}