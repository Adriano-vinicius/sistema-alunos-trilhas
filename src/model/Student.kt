package model

enum class Situacao(val label: String) {
    Ativo("Ativo"),
    Inativo("Inativo")
}
class Estudantes(
    var id: Int,
    var name: String,
    var email: String,
    var situation: Situacao = Situacao.Ativo
) {
    var trail: Trilha? = null
    var cursosConcluidos: Int = 0

    init {
        require(id > 0) { "O ID do aluno deve ser maior que zero." }
        require(name.isNotBlank()) { "O nome do aluno não pode estar em branco." }
        require(email.contains("@")) { "O e-mail do aluno precisa conter '@'." }
    }
    fun calcularPercentualProgresso(): Int {
        val trilhaDoAluno = trail
        if (trilhaDoAluno == null || trilhaDoAluno.cursos.isEmpty()) {
            return 0
        }
        val totalCursos = trilhaDoAluno.cursos.size
        return (cursosConcluidos * 100) / totalCursos
    }
}