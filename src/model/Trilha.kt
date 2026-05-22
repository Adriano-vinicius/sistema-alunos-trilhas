package model

enum class StatusTrilha(val label: String) {
    PLANEJADA("Planejada"),
    ATIVA("Ativa"),
    CONCLUIDA("Concluída"),
    ARQUIVADA("Arquivada")
}

class Trilha(
    val id: Int,
    val name: String,
    val descricao: String,
    val status: StatusTrilha = StatusTrilha.ATIVA
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

    fun calcularCargaHorariaTotal(): Int {
        var total = 0
        for (curso in _cursos) {
            total += curso.horas
        }
        return total
    }
}
