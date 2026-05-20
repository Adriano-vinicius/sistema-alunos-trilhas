package model

class AcademicService {
    val listaAlunos = ArrayList<Student>()
    val listaCursos = ArrayList<Course>()
    val listaTrilhas = ArrayList<Trail>()
    fun cadastrarAluno(aluno: Student): Boolean {
        for (a in listaAlunos) {
            if (a.id == aluno.id) {
                return false
            }
        }
        listaAlunos.add(aluno)
        return true
    }
    fun buscarAlunoPorId(idBuscado: Int): Student? {
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
    fun cadastrarTrilha(trilha: Trail): Boolean {
        for (t in listaTrilhas) {
            if (t.id == trilha.id) {
                return false
            }
        }
        listaTrilhas.add(trilha)
        return true
    }
    fun buscarTrilhaPorId(idBuscado: Int): Trail? {
        for (trilha in listaTrilhas) {
            if (trilha.id == idBuscado) {
                return trilha
            }
        }
        return null
    }
}