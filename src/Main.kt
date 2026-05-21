import model.*
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val servico = AcademicService()

    servico.cadastrarCurso(Course(1, "Kotlin Basico", 20, nivel.BASICO, CATEGORIA.PROGRAMACAO))
    servico.cadastrarCurso(Course(2, "Android com Compose", 40, nivel.AVANCADO, CATEGORIA.MOBILE))
    servico.cadastrarCurso(Course(3, "Design UI/UX Inicial", 15, nivel.INTERMEDIARIO, CATEGORIA.DESIGN))

    val trilhaMobile = Trilha(10, "Trilha de Mobile")
    trilhaMobile.addCursos(servico.buscarCursoPorId(1)!!)
    trilhaMobile.addCursos(servico.buscarCursoPorId(2)!!)
    servico.cadastrarTrilha(trilhaMobile)

    var opcao = -1

    while (opcao != 0) {
        println("\n--- MENU ---")
        println("1 - Cadastrar Aluno")
        println("2 - Listar Alunos")
        println("3 - Cadastrar Curso")
        println("4 - Listar Cursos")
        println("5 - Criar Trilha")
        println("6 - Adicionar Curso a uma Trilha")
        println("7 - Mostrar Relatorio da Trilha")
        println("8 - Matricular Aluno em uma Trilha")
        println("9 - Registrar Progresso de Cursos Concluidos")
        println("10 - Exibir Ranking de Alunos")
        println("0 - Sair")
        print("Escolha uma opcao: ")

        val entradaOpcao = scanner.nextLine().trim()
        val opcaoConvertida = entradaOpcao.toIntOrNull()

        if (opcaoConvertida == null) {
            println("❌ Erro: Escolha uma opcao digitando um numero valido!")
            esperarEnter(scanner)
            continue
        }

        opcao = opcaoConvertida

        when (opcao) {
            1 -> {
                print("ID do Aluno (Numero maior que zero): ")
                val entradaId = scanner.nextLine().trim()
                val id = entradaId.toIntOrNull()

                print("Nome do Aluno: ")
                val nome = scanner.nextLine().trim()

                print("E-mail do Aluno (Precisa conter @): ")
                val email = scanner.nextLine().trim()

                if (entradaId.isBlank() || nome.isBlank() || email.isBlank()) {
                    println("❌ Erro: Nenhum campo pode ser deixado em branco!")
                } else if (id == null || id <= 0) {
                    println("❌ Erro: O ID deve ser um numero valido maior que zero.")
                } else if (!email.contains("@")) {
                    println("❌ Erro: O e-mail precisa conter '@'.")
                } else {
                    val novoAluno = Estudantes(id, nome, email)
                    val deuCerto = servico.cadastrarAluno(novoAluno)
                    if (deuCerto) println("✓ Aluno cadastrado com sucesso!")
                    else println("❌ Erro: Ja existe um aluno com este ID.")
                }
                esperarEnter(scanner)
            }
            2 -> {
                println("\n--- LISTA DE ALUNOS ---")
                if (servico.listaAlunos.isEmpty()) {
                    println("Nenhum aluno cadastrado.")
                } else {
                    for (aluno in servico.listaAlunos) {
                        val nomeTrilha = aluno.trail?.name ?: "Nenhuma matricula"
                        println("ID: ${aluno.id} | Nome: ${aluno.name} | Trilha: $nomeTrilha | Concluidos: ${aluno.cursosConcluidos} cursos")
                    }
                }
                esperarEnter(scanner)
            }
            3 -> {
                val proximoIdSugerido = servico.obterProximoIdCurso()
                print("ID do Curso (Sugerido: $proximoIdSugerido): ")
                val entradaId = scanner.nextLine().trim()
                val id = entradaId.toIntOrNull()

                print("Titulo do Curso: ")
                val titulo = scanner.nextLine().trim()

                print("Carga Horaria (Maior que zero): ")
                var entradaHoras = scanner.nextLine().trim()

                if (entradaHoras.endsWith("h") || entradaHoras.endsWith("H")) {
                    entradaHoras = entradaHoras.substring(0, entradaHoras.length - 1).trim()
                }

                val horas = entradaHoras.toIntOrNull()

                if (entradaId.isBlank() || titulo.isBlank() || entradaHoras.isBlank()) {
                    println("❌ Erro: Nenhum campo pode ser deixado em branco!")
                } else if (id == null || id <= 0) {
                    println("❌ Erro: O ID deve ser um numero valido maior que zero.")
                } else if (horas == null || horas <= 0) {
                    println("❌ Erro: Carga horaria invalida. Digite um numero valido maior que zero.")
                } else {
                    val novoCurso = Course(id, titulo, horas, nivel.BASICO, CATEGORIA.PROGRAMACAO)
                    val deuCerto = servico.cadastrarCurso(novoCurso)
                    if (deuCerto) println("✓ Curso cadastrado com sucesso!")
                    else println("❌ Erro: Ja existe um curso com este ID.")
                }
                esperarEnter(scanner)
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
                esperarEnter(scanner)
            }
            5 -> {
                print("ID da Trilha (Numero maior que zero): ")
                val entradaId = scanner.nextLine().trim()
                val id = entradaId.toIntOrNull()

                print("Nome da Trilha: ")
                val nome = scanner.nextLine().trim()

                if (entradaId.isBlank() || nome.isBlank()) {
                    println("❌ Erro: Nenhum campo pode ser deixado em branco!")
                } else if (id == null || id <= 0) {
                    println("❌ Erro: O ID deve ser um numero valido maior que zero.")
                } else {
                    val novaTrilha = Trilha(id, nome)
                    val deuCerto = servico.cadastrarTrilha(novaTrilha)
                    if (deuCerto) println("✓ Trilha criada com sucesso!")
                    else println("❌ Erro: Ja existe uma trilha com este ID.")
                }
                esperarEnter(scanner)
            }
            6 -> {
                print("Digite o ID da Trilha: ")
                val entTrilha = scanner.nextLine().trim()
                print("Digite o ID do Curso: ")
                val entCurso = scanner.nextLine().trim()

                val idTrilha = entTrilha.toIntOrNull()
                val idCurso = entCurso.toIntOrNull()

                if (entTrilha.isBlank() || entCurso.isBlank()) {
                    println("❌ Erro: Nenhum campo pode ser deixado em branco!")
                } else if (idTrilha == null || idCurso == null) {
                    println("❌ Erro: Os IDs digitados precisam ser numeros validos.")
                } else {
                    val trilhaEncontrada = servico.buscarTrilhaPorId(idTrilha)
                    val cursoEncontrado = servico.buscarCursoPorId(idCurso)

                    if (trilhaEncontrada == null) {
                        println("❌ Erro: A Trilha com ID $idTrilha nao existe!")
                    } else if (cursoEncontrado == null) {
                        println("❌ Erro: O Curso com ID $idCurso nao existe!")
                    } else {
                        val adicionou = trilhaEncontrada.addCursos(cursoEncontrado)
                        if (adicionou) println("✓ Curso '${cursoEncontrado.title}' colocado na trilha '${trilhaEncontrada.name}'!")
                        else println("❌ Erro: Este curso ja foi adicionado nesta trilha antes.")
                    }
                }
                esperarEnter(scanner)
            }
            7 -> {
                print("Digite o ID da Trilha para ver o relatorio: ")
                val entTrilha = scanner.nextLine().trim()
                val idTrilha = entTrilha.toIntOrNull()

                if (entTrilha.isBlank()) {
                    println("❌ Erro: O ID nao pode ser deixado em branco!")
                } else if (idTrilha == null) {
                    println("❌ Erro: Digite um numero de ID valido.")
                } else {
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
                esperarEnter(scanner)
            }
            8 -> {
                print("Digite o ID do Aluno: ")
                val entAluno = scanner.nextLine().trim()
                print("Digite o ID da Trilha: ")
                val entTrilha = scanner.nextLine().trim()

                val idAluno = entAluno.toIntOrNull()
                val idTrilha = entTrilha.toIntOrNull()

                if (entAluno.isBlank() || entTrilha.isBlank()) {
                    println("❌ Erro: Nenhum campo pode ser deixado em branco!")
                } else if (idAluno == null || idTrilha == null) {
                    println("❌ Erro: Os IDs digitados precisam ser numeros validos.")
                } else {
                    val resultado = servico.matricularAlunoEmTrilha(idAluno, idTrilha)
                    println(resultado)
                }
                esperarEnter(scanner)
            }
            9 -> {
                print("Digite o ID do Aluno para concluir um curso da trilha: ")
                val entAluno = scanner.nextLine().trim()
                val idAluno = entAluno.toIntOrNull()

                if (entAluno.isBlank()) {
                    println("❌ Erro: O ID nao pode ser deixado em branco!")
                } else if (idAluno == null) {
                    println("❌ Erro: Digite um numero de ID valido.")
                } else {
                    val resultado = servico.registrarProgressoAluno(idAluno)
                    println(resultado)
                }
                esperarEnter(scanner)
            }
            10 -> {
                println("\n=== RANKING DE ESTUDANTES (POR PROGRESSO) ===")
                val ranking = servico.obterRankingEstudantes()

                if (ranking.isEmpty()) {
                    println("Nenhum estudante cadastrado para listar no ranking.")
                } else {
                    for (posicao in ranking.indices) {
                        val aluno = ranking[posicao]
                        println("${posicao + 1}º Lugar - Nome: ${aluno.name} | Progresso: ${aluno.calcularPercentualProgresso()}% (${aluno.cursosConcluidos} cursos concluidos)")
                    }
                }
                esperarEnter(scanner)
            }
            0 -> {
                println("Saindo do sistema...")
                print("Aperte enter para sair")
                scanner.nextLine()
            }
            else -> {
                println("Opcao invalida!")
                esperarEnter(scanner)
            }
        }
    }
}

fun esperarEnter(scanner: Scanner) {
    println("\nAperte enter para continuar...")
    scanner.nextLine()
}