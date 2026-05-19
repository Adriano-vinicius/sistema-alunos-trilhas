import model.*
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val listaAlunos = ArrayList<Student>()
    val listaCursos = ArrayList<Course>()

    var opcao = -1

    while (opcao != 0) {
        println("\n--- MENU SEMANA 02 ---")
        println("1 - Cadastrar Aluno")
        println("2 - Listar Alunos")
        println("3 - Cadastrar Curso")
        println("4 - Listar Cursos")
        println("0 - Sair")
        print("Escolha uma opção: ")

        opcao = scanner.nextInt()
        scanner.nextLine()

        when (opcao) {
            1 -> {
                try {
                    print("Digite o ID do Aluno (Número): ")
                    val id = scanner.nextInt()
                    scanner.nextLine()
                    print("Digite o Nome do Aluno: ")
                    val name = scanner.nextLine()
                    print("Digite o E-mail do Aluno: ")
                    val email = scanner.nextLine()
                    val novoAluno = Student(id, name, email)
                    listaAlunos.add(novoAluno)
                    println("✓ Aluno cadastrado com sucesso!")

                } catch (e: IllegalArgumentException) {
                    println("❌ Erro de Validação: ${e.message}")
                }
            }
            2 -> {
                println("\n--- LISTA DE ALUNOS ---")
                if (listaAlunos.isEmpty()) {
                    println("Nenhum aluno cadastrado.")
                } else {
                    for (aluno in listaAlunos) {
                        println("ID: ${aluno.id} | Nome: ${aluno.name} | E-mail: ${aluno.email} | Status: ${aluno.situation.label}")
                    }
                }
            }
            3 -> {
                try {
                    print("Digite o ID do Curso (Número): ")
                    val id = scanner.nextInt()
                    scanner.nextLine()
                    print("Digite o Título do Curso: ")
                    val title = scanner.nextLine()
                    print("Digite a Carga Horária: ")
                    val horas = scanner.nextInt()
                    val novoCurso = Course(id, title, horas, CourseLevel.BASIC, CourseCategory.PROGRAMMING)
                    listaCursos.add(novoCurso)
                    println("✓ Curso cadastrado com sucesso!")

                } catch (e: IllegalArgumentException) {
                    println("❌ Erro de Validação: ${e.message}")
                }
            }
            4 -> {
                println("\n--- LISTA DE CURSOS ---")
                if (listaCursos.isEmpty()) {
                    println("Nenhum curso cadastrado.")
                } else {
                    for (curso in listaCursos) {
                        println("ID: ${curso.id} | Título: ${curso.title} | Horas: ${curso.workloadHours}h")
                    }
                }
            }
            0 -> println("Saindo do sistema...")
            else -> println("Opção inválida!")
        }
    }
}