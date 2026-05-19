package model

class Trail(
    val id: Int,
    val name: String
) {
    val courses = mutableListOf<Course>()

    fun addCourse(course: Course) {
        courses.add(course)
    }
}