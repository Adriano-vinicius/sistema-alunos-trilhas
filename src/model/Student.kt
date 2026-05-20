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

    init {
        require(id > 0) { "O ID do aluno deve ser maior que zero." }
        require(name.isNotBlank()) { "O nome do aluno não pode estar em branco." }
        require(email.contains("@")) { "O e-mail do aluno precisa conter '@'." }
    }
}