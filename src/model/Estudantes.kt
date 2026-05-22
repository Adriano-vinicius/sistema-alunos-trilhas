package model

enum class Situacao(val label: String) {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    BLOQUEADO("Bloqueado")
}

data class Estudantes(
    val id: Int,
    val name: String,
    val email: String,
    val situation: Situacao = Situacao.ATIVO
) {
    init {
        require(id > 0) { "O ID do aluno deve ser maior que zero." }
        require(name.trim().length >= 3) { "O nome do aluno deve ter pelo menos 3 caracteres." }
        require(email.contains("@") && email.contains(".")) { "O e-mail do aluno precisa conter '@' e '.'." }
    }
}
