import model.*
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val servico = AcademicService()
    servico.cadastrarCurso(Course(1, "Kotlin Basico", 20, nivel.BASICO, CATEGORIA.PROGRAMACAO))
    servico.cadastrarCurso(Course(2, "Android com Compose", 40, nivel.AVANCADO, CATEGORIA.MOBILE))
    servico.cadastrarTrilha(Trilha(10, "Trilha de Mobile"))

    var opcao = -1

    while (opcao != 0) {
        println("\\n--- MENU ---")
        println("1 - Cadastrar Aluno")
        println("2 - Listar Alunos")
        println("3 - Cadastrar Curso")
        println("4 - Listar Cursos")
        println("5 - Criar Trilha")
        println("6 - Adicionar Curso a uma Trilha")
        println("7 - Mostrar Relatorio da Trilha")
        println("0 - Sair")
        print("Escolha uma opcao: ")

        opcao = scanner.nextInt()
        scanner.nextLine()

        when (opcao) {
            1 -> {
                print("ID do Aluno (Numero maior que zero): ")
                val id = scanner.nextInt()
                scanner.nextLine()
                print("Nome do Aluno: ")
                val nome = scanner.nextLine()
                print("E-mail do Aluno (Precisa conter @): ")
                val email = scanner.nextLine()
                if (id <= 0) {
                    println("❌ Erro: O ID deve ser maior que zero.")
                } else if (nome.isBlank()) {
                    println("❌ Erro: O nome nao pode estar em branco.")
                } else if (!email.contains("@")) {
                    println("❌ Erro: O e-mail precisa conter '@'.")
                } else {
                    val novoAluno = Estudantes(id, nome, email)
                    val deuCerto = servico.cadastrarAluno(novoAluno)

                    if (deuCerto) {
                        println("✓ Aluno cadastrado com sucesso!")
                    } else {
                        println("❌ Erro: Ja existe um aluno com este ID.")
                    }
                }
            }
            2 -> {
                println("\n--- LISTA DE ALUNOS ---")
                if (servico.listaAlunos.isEmpty()) {
                    println("Nenhum aluno cadastrado.")
                } else {
                    for (aluno in servico.listaAlunos) {
                        println("ID: ${aluno.id} | Nome: ${aluno.name} | E-mail: ${aluno.email}")
                    }
                }
            }
            3 -> {
                print("ID do Curso (Numero maior que zero): ")
                val id = scanner.nextInt()
                scanner.nextLine()
                print("Titulo do Curso: ")
                val titulo = scanner.nextLine()
                print("Carga Horaria (Maior que zero): ")
                val horas = scanner.nextInt()

                if (id <= 0) {
                    println("❌ Erro: O ID deve ser maior que zero.")
                } else if (titulo.isBlank()) {
                    println("❌ Erro: O titulo nao pode estar em branco.")
                } else if (horas <= 0) {
                    println("❌ Erro: A carga horaria deve ser maior que zero.")
                } else {
                    val novoCurso = Course(id, titulo, horas, nivel.BASICO, CATEGORIA.PROGRAMACAO)
                    val deuCerto = servico.cadastrarCurso(novoCurso)

                    if (deuCerto) {
                        println("✓ Curso cadastrado com sucesso!")
                    } else {
                        println("❌ Erro: Ja existe um curso com este ID.")
                    }
                }
            }
            4 -> {
                println("\n--- LISTA DE CURSOS ---")
                if (servico.listaCursos.isEmpty()) {
                    println("Nenhum curso cadastrado.")
                } else {
                    for (curso in servico.listaCursos) {
                        println("ID: ${curso.id} | Titulo: ${curso.title} | Horas: ${curso.horas}h")
                    }
                }
            }
            5 -> {
                print("ID da Trilha (Numero maior que zero): ")
                val id = scanner.nextInt()
                scanner.nextLine()
                print("Nome da Trilha: ")
                val nome = scanner.nextLine()

                if (id <= 0) {
                    println("❌ Erro: O ID deve ser maior que zero.")
                } else if (nome.isBlank()) {
                    println("❌ Erro: O nome nao pode estar em branco.")
                } else {
                    val novaTrilha = Trilha(id, nome)
                    val deuCerto = servico.cadastrarTrilha(novaTrilha)

                    if (deuCerto) {
                        println("✓ Trilha criada com sucesso!")
                    } else {
                        println("❌ Erro: Ja existe uma trilha com este ID.")
                    }
                }
            }
            6 -> {
                print("Digite o ID da Trilha: ")
                val idTrilha = scanner.nextInt()
                print("Digite o ID do Curso: ")
                val idCurso = scanner.nextInt()

                val trilhaEncontrada = servico.buscarTrilhaPorId(idTrilha)
                val cursoEncontrado = servico.buscarCursoPorId(idCurso)
                if (trilhaEncontrada == null) {
                    println("❌ Erro: A Trilha com ID $idTrilha nao existe!")
                } else if (cursoEncontrado == null) {
                    println("❌ Erro: O Curso com ID $idCurso nao existe!")
                } else {
                    val adicionou = trilhaEncontrada.addCursos(cursoEncontrado)
                    if (adicionou) {
                        println("✓ Curso '${cursoEncontrado.title}' colocado na trilha '${trilhaEncontrada.name}'!")
                    } else {
                        println("❌ Erro: Este curso ja foi adicionado nesta trilha antes (Duplicidade barrada)!")
                    }
                }
            }
            7 -> {
                print("Digite o ID da Trilha para ver o relatorio: ")
                val idTrilha = scanner.nextInt()
                val trilhaEncontrada = servico.buscarTrilhaPorId(idTrilha)

                if (trilhaEncontrada == null) {
                    println("❌ Erro: Trilha nao encontrada.")
                } else {
                    println("\n--- RELATORIO DA TRILHA: ${trilhaEncontrada.name} ---")
                    if (trilhaEncontrada.cursos.isEmpty()) {
                        println("Esta trilha ainda nao tem cursos.")
                    } else {
                        for (curso in trilhaEncontrada.cursos) {
                            println("  • ID: ${curso.id} | ${curso.title} (${curso.horas}h)")
                        }
                    }
                }
            }
            0 -> println("Saindo do sistema...")
            else -> println("Opcao invalida!")
        }
    }
}