package service

import model.*

class AcademicService {
    private val _listaAlunos = ArrayList<Estudantes>()
    private val _listaCursos = ArrayList<Course>()
    private val _listaTrilhas = ArrayList<Trilha>()
    private val _listaMatriculas = ArrayList<Matricula>()

    val listaAlunos: List<Estudantes> get() = _listaAlunos
    val listaCursos: List<Course> get() = _listaCursos
    val listaTrilhas: List<Trilha> get() = _listaTrilhas
    val listaMatriculas: List<Matricula> get() = _listaMatriculas

    fun cadastrarAluno(aluno: Estudantes): Boolean {
        for (a in _listaAlunos) {
            if (a.id == aluno.id) return false
        }
        _listaAlunos.add(aluno)
        return true
    }

    fun buscarAlunoPorId(idBuscado: Int): Estudantes? {
        for (aluno in _listaAlunos) {
            if (aluno.id == idBuscado) return aluno
        }
        return null
    }

    fun cadastrarCurso(curso: Course): Boolean {
        for (c in _listaCursos) {
            if (c.id == curso.id) return false
        }
        _listaCursos.add(curso)
        return true
    }

    fun buscarCursoPorId(idBuscado: Int): Course? {
        for (curso in _listaCursos) {
            if (curso.id == idBuscado) return curso
        }
        return null
    }

    fun cadastrarTrilha(trilha: Trilha): Boolean {
        for (t in _listaTrilhas) {
            if (t.id == trilha.id) return false
        }
        _listaTrilhas.add(trilha)
        return true
    }

    fun buscarTrilhaPorId(idBuscado: Int): Trilha? {
        for (trilha in _listaTrilhas) {
            if (trilha.id == idBuscado) return trilha
        }
        return null
    }

    fun buscarMatricula(idAluno: Int, idTrilha: Int): Matricula? {
        for (matricula in _listaMatriculas) {
            if (matricula.aluno.id == idAluno && matricula.trilha.id == idTrilha) {
                return matricula
            }
        }
        return null
    }

    fun adicionarCursoNaTrilha(idTrilha: Int, idCurso: Int): String {
        val trilha = buscarTrilhaPorId(idTrilha)
        val curso = buscarCursoPorId(idCurso)

        if (trilha == null) return "❌ Erro: Trilha não encontrada."
        if (curso == null) return "❌ Erro: Curso não encontrado."

        if (trilha.status == StatusTrilha.CONCLUIDA || trilha.status == StatusTrilha.ARQUIVADA) {
            return "❌ Erro: Trilhas concluídas ou arquivadas não podem receber novos cursos."
        }

        val adicionou = trilha.addCursos(curso)
        if (!adicionou) return "❌ Erro: Curso já associado a esta trilha."

        return "✓ Curso adicionado à trilha com sucesso."
    }

    fun matricularAlunoEmTrilha(idAluno: Int, idTrilha: Int): String {
        val aluno = buscarAlunoPorId(idAluno)
        val trilha = buscarTrilhaPorId(idTrilha)

        if (aluno == null) return "❌ Erro: Aluno não encontrado."
        if (trilha == null) return "❌ Erro: Trilha não encontrada."

        if (aluno.situation != Situacao.ATIVO) {
            return "❌ Erro: Apenas alunos ativos podem ser matriculados."
        }

        if (trilha.status != StatusTrilha.ATIVA) {
            return "❌ Erro: Apenas trilhas ativas aceitam matrícula."
        }

        val matriculaExistente = buscarMatricula(idAluno, idTrilha)
        if (matriculaExistente != null) {
            return "❌ Erro: Este aluno já possui matrícula nesta trilha."
        }

        _listaMatriculas.add(Matricula(aluno, trilha))
        return "✓ Matrícula realizada com sucesso."
    }

    fun registrarProgressoAluno(idAluno: Int, idTrilha: Int, quantidadeConcluida: Int): String {
        val matricula = buscarMatricula(idAluno, idTrilha)

        if (matricula == null || matricula.status != StatusMatricula.ATIVA) {
            return "❌ Erro: Não existe matrícula ativa entre este aluno e esta trilha."
        }

        val totalCursosDaTrilha = matricula.trilha.cursos.size
        if (totalCursosDaTrilha == 0) return "❌ Erro: A trilha não possui cursos cadastrados."
        if (quantidadeConcluida < 0) return "❌ Erro: A quantidade de cursos concluídos não pode ser negativa."
        if (quantidadeConcluida > totalCursosDaTrilha) {
            return "❌ Erro: A quantidade concluída não pode ser maior que o total de cursos da trilha."
        }

        matricula.cursosConcluidos = quantidadeConcluida

        if (matricula.calcularPercentualProgresso() == 100) {
            matricula.status = StatusMatricula.CONCLUIDA
        }

        return "✓ Progresso registrado com sucesso. Percentual atual: ${matricula.calcularPercentualProgresso()}%."
    }

    fun obterRankingMatriculas(): ArrayList<Matricula> {
        val ranking = ArrayList<Matricula>()
        for (matricula in _listaMatriculas) {
            ranking.add(matricula)
        }

        for (i in 0 until ranking.size) {
            for (j in 0 until ranking.size - 1 - i) {
                val matriculaA = ranking[j]
                val matriculaB = ranking[j + 1]

                val progressoA = matriculaA.calcularPercentualProgresso()
                val progressoB = matriculaB.calcularPercentualProgresso()

                var precisaTrocar = false

                if (progressoA < progressoB) {
                    precisaTrocar = true
                } else if (progressoA == progressoB) {
                    if (matriculaA.aluno.name.lowercase() > matriculaB.aluno.name.lowercase()) {
                        precisaTrocar = true
                    }
                }

                if (precisaTrocar) {
                    ranking[j] = matriculaB
                    ranking[j + 1] = matriculaA
                }
            }
        }
        return ranking
    }

    fun obterMatriculasDaTrilha(idTrilha: Int): ArrayList<Matricula> {
        val resultado = ArrayList<Matricula>()
        for (matricula in _listaMatriculas) {
            if (matricula.trilha.id == idTrilha) {
                resultado.add(matricula)
            }
        }
        return resultado
    }

    fun obterAlunosSemMatricula(): ArrayList<Estudantes> {
        val resultado = ArrayList<Estudantes>()

        for (aluno in _listaAlunos) {
            var possuiMatricula = false
            for (matricula in _listaMatriculas) {
                if (matricula.aluno.id == aluno.id) {
                    possuiMatricula = true
                }
            }
            if (!possuiMatricula) resultado.add(aluno)
        }
        return resultado
    }

    fun obterProximoIdAluno(): Int {
        var maiorId = 0
        for (aluno in _listaAlunos) {
            if (aluno.id > maiorId) maiorId = aluno.id
        }
        return maiorId + 1
    }

    fun obterProximoIdCurso(): Int {
        var maiorId = 0
        for (curso in _listaCursos) {
            if (curso.id > maiorId) maiorId = curso.id
        }
        return maiorId + 1
    }

    fun obterProximoIdTrilha(): Int {
        var maiorId = 0
        for (trilha in _listaTrilhas) {
            if (trilha.id > maiorId) maiorId = trilha.id
        }
        return maiorId + 1
    }
}
