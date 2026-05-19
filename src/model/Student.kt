package model
enum class StudentSituation(val label: String) {
    ACTIVE("Ativo"),
    INACTIVE("Inativo")
}

class Student(
    var id: Int,
    var name: String,
    var email: String,
    var situation: StudentSituation = StudentSituation.ACTIVE
) {
    var trail: Trail? = null

    init {
        require(id > 0) { "O ID do aluno deve ser maior que zero." }
        require(name.isNotBlank()) { "O nome do aluno não pode estar em branco." }
        require(email.contains("@")) { "O e-mail do aluno precisa conter '@'." }
    }
}