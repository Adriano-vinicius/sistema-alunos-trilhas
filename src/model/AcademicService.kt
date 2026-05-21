package model

class AcademicService {
    val listaAlunos = ArrayList<Estudantes>()
    val listaCursos = ArrayList<Course>()
    val listaTrilhas = ArrayList<Trilha>()

    fun cadastrarAluno(aluno: Estudantes): Boolean {
        for (a in listaAlunos) {
            if (a.id == aluno.id) return false
        }
        listaAlunos.add(aluno)
        return true
    }

    fun buscarAlunoPorId(idBuscado: Int): Estudantes? {
        for (aluno in listaAlunos) {
            if (aluno.id == idBuscado) return aluno
        }
        return null
    }

    fun cadastrarCurso(curso: Course): Boolean {
        for (c in listaCursos) {
            if (c.id == curso.id) return false
        }
        listaCursos.add(curso)
        return true
    }

    fun buscarCursoPorId(idBuscado: Int): Course? {
        for (curso in listaCursos) {
            if (curso.id == idBuscado) return curso
        }
        return null
    }

    fun cadastrarTrilha(trilha: Trilha): Boolean {
        for (t in listaTrilhas) {
            if (t.id == trilha.id) return false
        }
        listaTrilhas.add(trilha)
        return true
    }

    fun buscarTrilhaPorId(idBuscado: Int): Trilha? {
        for (trilha in listaTrilhas) {
            if (trilha.id == idBuscado) return trilha
        }
        return null
    }

    fun matricularAlunoEmTrilha(idAluno: Int, idTrilha: Int): String {
        val aluno = buscarAlunoPorId(idAluno)
        val trilha = buscarTrilhaPorId(idTrilha)

        if (aluno == null) return "❌ Erro: Aluno não encontrado."
        if (trilha == null) return "❌ Erro: Trilha não encontrada."

        aluno.trail = trilha
        aluno.cursosConcluidos = 0
        return "✓ Aluno '${aluno.name}' matriculado com sucesso na trilha '${trilha.name}'!"
    }
    fun registrarProgressoAluno(idAluno: Int): String {
        val aluno = buscarAlunoPorId(idAluno)
        if (aluno == null) return "❌ Erro: Aluno não encontrado."

        val trilhaDoAluno = aluno.trail
        if (trilhaDoAluno == null) return "❌ Erro: Este aluno não está matriculado em nenhuma trilha!"

        val totalCursosDaTrilha = trilhaDoAluno.cursos.size
        if (totalCursosDaTrilha == 0) return "❌ Erro: A trilha associada não possui nenhum curso cadastrado ainda!"
        if (aluno.cursosConcluidos >= totalCursosDaTrilha) {
            return "❌ Erro: O aluno já concluiu todos os $totalCursosDaTrilha cursos desta trilha!"
        }
        aluno.cursosConcluidos++
        return "✓ Progresso registrado! Agora o aluno concluiu ${aluno.cursosConcluidos}/$totalCursosDaTrilha cursos (${aluno.calcularPercentualProgresso()}%)."
    }
    fun obterRankingEstudantes(): ArrayList<Estudantes> {
        val ranking = ArrayList<Estudantes>()
        for (aluno in listaAlunos) {
            ranking.add(aluno)
        }
        for (i in 0 until ranking.size) {
            for (j in 0 until ranking.size - 1 - i) {
                val alunoA = ranking[j]
                val alunoB = ranking[j + 1]

                val progressoA = alunoA.calcularPercentualProgresso()
                val progressoB = alunoB.calcularPercentualProgresso()

                var precisaTrocar = false

                if (progressoA < progressoB) {
                    precisaTrocar = true
                } else if (progressoA == progressoB) {
                    if (alunoA.name.lowercase() > alunoB.name.lowercase()) {
                        precisaTrocar = true
                    }
                }

                if (precisaTrocar) {
                    ranking[j] = alunoB
                    ranking[j + 1] = alunoA
                }
            }
        }
        return ranking
    }
    fun obterProximoIdCurso(): Int {
        var maiorId = 0
        for (curso in listaCursos) {
            if (curso.id > maiorId) {
                maiorId = curso.id
            }
        }
        return maiorId + 1
    }
}