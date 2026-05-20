package model

class Trilha(
    var id: Int,
    var name: String
) {
    private val _cursos = ArrayList<Course>()
    val cursos: List<Course> get() = _cursos

    init {
        require(id > 0) { "O ID da trilha deve ser maior que zero." }
        require(name.isNotBlank()) { "O nome da trilha não pode estar em branco." }
    }
    fun addCursos(curso: Course): Boolean {
        val jaExiste = _cursos.any { it.id == curso.id }
        return if (jaExiste) {
            false
        } else {
            _cursos.add(curso)
            true
        }
    }
}