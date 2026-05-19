class Trail(
    var id: Int,
    var name: String
) {
    var courses = ArrayList<Course>()

    fun addCourse(course: Course) {
        courses.add(course)
    }
}