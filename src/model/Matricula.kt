package model

enum class StatusMatricula(val label: String) {
    ATIVA("Ativa"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada")
}

data class Matricula(
    val aluno: Estudantes,
    val trilha: Trilha,
    var cursosConcluidos: Int = 0,
    var status: StatusMatricula = StatusMatricula.ATIVA
) {
    fun calcularPercentualProgresso(): Int {
        if (trilha.cursos.isEmpty()) return 0
        return (cursosConcluidos * 100) / trilha.cursos.size
    }
}
