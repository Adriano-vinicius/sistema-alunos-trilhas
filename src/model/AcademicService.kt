package model

class AcademicService {
    val listaAlunos = ArrayList<Estudantes>()
    val listaCursos = ArrayList<Course>()
    val listaTrilhas = ArrayList<Trilha>()
    fun cadastrarAluno(aluno: Estudantes): Boolean {
        for (a in listaAlunos) {
            if (a.id == aluno.id) {
                return false
            }
        }
        listaAlunos.add(aluno)
        return true
    }
    fun buscarAlunoPorId(idBuscado: Int): Estudantes? {
        for (aluno in listaAlunos) {
            if (aluno.id == idBuscado) {
                return aluno
            }
        }
        return null
    }
    fun cadastrarCurso(curso: Course): Boolean {
        for (c in listaCursos) {
            if (c.id == curso.id) {
                return false
            }
        }
        listaCursos.add(curso)
        return true
    }
    fun buscarCursoPorId(idBuscado: Int): Course? {
        for (curso in listaCursos) {
            if (curso.id == idBuscado) {
                return curso
            }
        }
        return null
    }
    fun cadastrarTrilha(trilha: Trilha): Boolean {
        for (t in listaTrilhas) {
            if (t.id == trilha.id) {
                return false
            }
        }
        listaTrilhas.add(trilha)
        return true
    }
    fun buscarTrilhaPorId(idBuscado: Int): Trilha? {
        for (trilha in listaTrilhas) {
            if (trilha.id == idBuscado) {
                return trilha
            }
        }
        return null
    }
}