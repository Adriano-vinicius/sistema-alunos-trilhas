package app

import model.*
import service.AcademicService
import java.util.Scanner

class ConsoleApp {
    private val scanner = Scanner(System.`in`)
    private val servico = AcademicService()

    fun iniciar() {
        cadastrarDadosIniciais()

        var opcao = -1
        println("Sistema de Alunos e Trilhas")

        while (opcao != 11) {
            mostrarMenuPrincipal()
            val opcaoConvertida = lerInteiroObrigatorio("Escolha uma opção: ")

            if (opcaoConvertida == null) {
                esperarEnter()
                continue
            }

            opcao = opcaoConvertida

            when (opcao) {
                1 -> { cadastrarAluno(); esperarEnter() }
                2 -> { listarAlunos(); esperarEnter() }
                3 -> { cadastrarCurso(); esperarEnter() }
                4 -> { listarCursos(); esperarEnter() }
                5 -> { cadastrarTrilha(); esperarEnter() }
                6 -> { listarTrilhas(); esperarEnter() }
                7 -> { adicionarCursoNaTrilha(); esperarEnter() }
                8 -> { matricularAlunoEmTrilha(); esperarEnter() }
                9 -> { registrarProgresso(); esperarEnter() }
                10 -> abrirMenuRelatorios()
                11 -> println("Sistema encerrado.")
                else -> {
                    println("Opção inválida. Tente novamente.")
                    esperarEnter()
                }
            }
        }
    }

    private fun cadastrarDadosIniciais() {
        servico.cadastrarCurso(Course(1, "Kotlin Básico", 20, Nivel.BASICO, Categoria.KOTLIN))
        servico.cadastrarCurso(Course(2, "Android com Compose", 40, Nivel.AVANCADO, Categoria.ANDROID))
        servico.cadastrarCurso(Course(3, "Design UI/UX Inicial", 15, Nivel.INTERMEDIARIO, Categoria.DESIGN))

        servico.cadastrarTrilha(Trilha(1, "Trilha Android", "Base para desenvolvimento mobile", StatusTrilha.ATIVA))
        servico.adicionarCursoNaTrilha(1, 1)
        servico.adicionarCursoNaTrilha(1, 2)
    }

    private fun mostrarMenuPrincipal() {
        println("\n--- MENU PRINCIPAL ---")
        println("1 - Cadastrar aluno")
        println("2 - Listar alunos")
        println("3 - Cadastrar curso")
        println("4 - Listar cursos")
        println("5 - Cadastrar trilha")
        println("6 - Listar trilhas")
        println("7 - Adicionar curso a uma trilha")
        println("8 - Matricular aluno em trilha")
        println("9 - Registrar progresso do aluno")
        println("10 - Exibir relatórios")
        println("11 - Sair")
    }

    private fun cadastrarAluno() {
        println("\n--- CADASTRO DE ALUNO ---")
        val id = lerInteiroObrigatorio("ID do aluno (sugerido: ${servico.obterProximoIdAluno()}): ") ?: return
        val nome = lerTextoObrigatorio("Nome completo: ") ?: return
        val email = lerTextoObrigatorio("E-mail: ") ?: return
        val situacao = escolherSituacaoAluno() ?: return

        if (id <= 0) {
            println("❌ Erro: O ID deve ser maior que zero.")
            return
        }
        if (nome.trim().length < 3) {
            println("❌ Erro: O nome deve ter pelo menos 3 caracteres.")
            return
        }
        if (!email.contains("@") || !email.contains(".")) {
            println("❌ Erro: O e-mail precisa conter '@' e '.'.")
            return
        }

        val aluno = Estudantes(id, nome, email, situacao)
        val deuCerto = servico.cadastrarAluno(aluno)

        if (deuCerto) println("✓ Aluno cadastrado com sucesso.")
        else println("❌ Erro: Já existe aluno com este ID.")
    }

    private fun listarAlunos() {
        println("\n--- LISTA DE ALUNOS ---")
        if (servico.listaAlunos.isEmpty()) {
            println("Nenhum aluno cadastrado.")
            return
        }

        for (aluno in servico.listaAlunos) {
            println("ID: ${aluno.id} | Nome: ${aluno.name} | E-mail: ${aluno.email} | Situação: ${aluno.situation.label}")
        }
    }

    private fun cadastrarCurso() {
        println("\n--- CADASTRO DE CURSO ---")
        val id = lerInteiroObrigatorio("ID do curso (sugerido: ${servico.obterProximoIdCurso()}): ") ?: return
        val titulo = lerTextoObrigatorio("Título do curso: ") ?: return
        val horas = lerCargaHoraria() ?: return
        val nivel = escolherNivelCurso() ?: return
        val categoria = escolherCategoriaCurso() ?: return

        if (id <= 0) {
            println("❌ Erro: O ID deve ser maior que zero.")
            return
        }
        if (horas <= 0) {
            println("❌ Erro: A carga horária deve ser maior que zero.")
            return
        }

        val curso = Course(id, titulo, horas, nivel, categoria)
        val deuCerto = servico.cadastrarCurso(curso)

        if (deuCerto) println("✓ Curso cadastrado com sucesso.")
        else println("❌ Erro: Já existe curso com este ID.")
    }

    private fun listarCursos() {
        println("\n--- LISTA DE CURSOS ---")
        if (servico.listaCursos.isEmpty()) {
            println("Nenhum curso cadastrado.")
            return
        }

        for (curso in servico.listaCursos) {
            println("ID: ${curso.id} | Título: ${curso.title} | Carga: ${curso.horas}h | Nível: ${curso.level.label} | Categoria: ${curso.category.label}")
        }
    }

    private fun cadastrarTrilha() {
        println("\n--- CADASTRO DE TRILHA ---")
        val id = lerInteiroObrigatorio("ID da trilha (sugerido: ${servico.obterProximoIdTrilha()}): ") ?: return
        val nome = lerTextoObrigatorio("Nome da trilha: ") ?: return
        val descricao = lerTextoObrigatorio("Descrição curta: ") ?: return
        val status = escolherStatusTrilha() ?: return

        if (id <= 0) {
            println("❌ Erro: O ID deve ser maior que zero.")
            return
        }

        val trilha = Trilha(id, nome, descricao, status)
        val deuCerto = servico.cadastrarTrilha(trilha)

        if (deuCerto) println("✓ Trilha cadastrada com sucesso.")
        else println("❌ Erro: Já existe trilha com este ID.")
    }

    private fun listarTrilhas() {
        println("\n--- LISTA DE TRILHAS ---")
        if (servico.listaTrilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
            return
        }

        for (trilha in servico.listaTrilhas) {
            println("ID: ${trilha.id} | Nome: ${trilha.name} | Status: ${trilha.status.label} | Cursos: ${trilha.cursos.size} | Carga total: ${trilha.calcularCargaHorariaTotal()}h")
        }
    }

    private fun adicionarCursoNaTrilha() {
        println("\n--- ADICIONAR CURSO A UMA TRILHA ---")
        val idTrilha = lerInteiroObrigatorio("Digite o ID da trilha: ") ?: return
        val idCurso = lerInteiroObrigatorio("Digite o ID do curso: ") ?: return

        val resultado = servico.adicionarCursoNaTrilha(idTrilha, idCurso)
        println(resultado)
    }

    private fun matricularAlunoEmTrilha() {
        println("\n--- MATRÍCULA DE ALUNO EM TRILHA ---")
        val idAluno = lerInteiroObrigatorio("Digite o ID do aluno: ") ?: return
        val idTrilha = lerInteiroObrigatorio("Digite o ID da trilha: ") ?: return

        val resultado = servico.matricularAlunoEmTrilha(idAluno, idTrilha)
        println(resultado)
    }

    private fun registrarProgresso() {
        println("\n--- REGISTRAR PROGRESSO ---")
        val idAluno = lerInteiroObrigatorio("Digite o ID do aluno: ") ?: return
        val idTrilha = lerInteiroObrigatorio("Digite o ID da trilha: ") ?: return
        val quantidade = lerInteiroObrigatorio("Quantidade total de cursos concluídos até agora: ") ?: return

        val resultado = servico.registrarProgressoAluno(idAluno, idTrilha, quantidade)
        println(resultado)
    }

    private fun abrirMenuRelatorios() {
        var opcao = -1

        while (opcao != 7) {
            println("\n--- MENU DE RELATÓRIOS ---")
            println("1 - Alunos cadastrados")
            println("2 - Cursos cadastrados")
            println("3 - Trilhas com carga horária total")
            println("4 - Alunos matriculados por trilha")
            println("5 - Ranking de progresso")
            println("6 - Alunos sem matrícula")
            println("7 - Voltar ao menu principal")

            val opcaoConvertida = lerInteiroObrigatorio("Escolha uma opção: ")
            if (opcaoConvertida == null) {
                esperarEnter()
                continue
            }

            opcao = opcaoConvertida

            when (opcao) {
                1 -> { listarAlunos(); esperarEnter() }
                2 -> { listarCursos(); esperarEnter() }
                3 -> { relatorioTrilhasComCargaHoraria(); esperarEnter() }
                4 -> { relatorioAlunosPorTrilha(); esperarEnter() }
                5 -> { relatorioRanking(); esperarEnter() }
                6 -> { relatorioAlunosSemMatricula(); esperarEnter() }
                7 -> println("Voltando ao menu principal...")
                else -> {
                    println("Opção inválida. Tente novamente.")
                    esperarEnter()
                }
            }
        }
    }

    private fun relatorioTrilhasComCargaHoraria() {
        println("\n--- RELATÓRIO DE TRILHAS COM CARGA HORÁRIA TOTAL ---")
        if (servico.listaTrilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
            return
        }

        for (trilha in servico.listaTrilhas) {
            println("Trilha: ${trilha.name} | Status: ${trilha.status.label} | Cursos: ${trilha.cursos.size} | Carga total: ${trilha.calcularCargaHorariaTotal()}h")
        }
    }

    private fun relatorioAlunosPorTrilha() {
        println("\n--- RELATÓRIO DE ALUNOS POR TRILHA ---")
        if (servico.listaTrilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
            return
        }

        for (trilha in servico.listaTrilhas) {
            println("\nTrilha: ${trilha.name}")
            val matriculas = servico.obterMatriculasDaTrilha(trilha.id)

            if (matriculas.isEmpty()) {
                println("Nenhum aluno matriculado nesta trilha.")
            } else {
                for (matricula in matriculas) {
                    println("- ${matricula.aluno.name} | Progresso: ${matricula.calcularPercentualProgresso()}% | Status: ${matricula.status.label}")
                }
            }
        }
    }

    private fun relatorioRanking() {
        println("\n--- RANKING DE PROGRESSO ---")
        val ranking = servico.obterRankingMatriculas()

        if (ranking.isEmpty()) {
            println("Nenhuma matrícula cadastrada para gerar ranking.")
            return
        }

        for (posicao in ranking.indices) {
            val matricula = ranking[posicao]
            println("${posicao + 1}º lugar - Aluno: ${matricula.aluno.name} | Trilha: ${matricula.trilha.name} | Progresso: ${matricula.calcularPercentualProgresso()}% | Status: ${matricula.status.label}")
        }
    }

    private fun relatorioAlunosSemMatricula() {
        println("\n--- ALUNOS SEM MATRÍCULA ---")
        val alunosSemMatricula = servico.obterAlunosSemMatricula()

        if (alunosSemMatricula.isEmpty()) {
            println("Todos os alunos possuem matrícula.")
            return
        }

        for (aluno in alunosSemMatricula) {
            println("ID: ${aluno.id} | Nome: ${aluno.name} | E-mail: ${aluno.email} | Situação: ${aluno.situation.label}")
        }
    }

    private fun escolherSituacaoAluno(): Situacao? {
        println("Situação do aluno:")
        val opcoes = Situacao.values()
        for (i in opcoes.indices) {
            println("${i + 1} - ${opcoes[i].label}")
        }
        val opcao = lerInteiroObrigatorio("Escolha a situação: ") ?: return null
        if (opcao < 1 || opcao > opcoes.size) {
            println("❌ Erro: Situação inválida.")
            return null
        }
        return opcoes[opcao - 1]
    }

    private fun escolherNivelCurso(): Nivel? {
        println("Nível do curso:")
        val opcoes = Nivel.values()
        for (i in opcoes.indices) {
            println("${i + 1} - ${opcoes[i].label}")
        }
        val opcao = lerInteiroObrigatorio("Escolha o nível: ") ?: return null
        if (opcao < 1 || opcao > opcoes.size) {
            println("❌ Erro: Nível inválido.")
            return null
        }
        return opcoes[opcao - 1]
    }

    private fun escolherCategoriaCurso(): Categoria? {
        println("Categoria do curso:")
        val opcoes = Categoria.values()
        for (i in opcoes.indices) {
            println("${i + 1} - ${opcoes[i].label}")
        }
        val opcao = lerInteiroObrigatorio("Escolha a categoria: ") ?: return null
        if (opcao < 1 || opcao > opcoes.size) {
            println("❌ Erro: Categoria inválida.")
            return null
        }
        return opcoes[opcao - 1]
    }

    private fun escolherStatusTrilha(): StatusTrilha? {
        println("Status da trilha:")
        val opcoes = StatusTrilha.values()
        for (i in opcoes.indices) {
            println("${i + 1} - ${opcoes[i].label}")
        }
        val opcao = lerInteiroObrigatorio("Escolha o status: ") ?: return null
        if (opcao < 1 || opcao > opcoes.size) {
            println("❌ Erro: Status inválido.")
            return null
        }
        return opcoes[opcao - 1]
    }

    private fun lerCargaHoraria(): Int? {
        print("Carga horária (ex: 30 ou 30h): ")
        var entrada = scanner.nextLine().trim()

        if (entrada.isBlank()) {
            println("❌ Erro: A carga horária não pode ficar em branco.")
            return null
        }

        if (entrada.endsWith("h") || entrada.endsWith("H")) {
            entrada = entrada.substring(0, entrada.length - 1).trim()
        }

        val numero = entrada.toIntOrNull()
        if (numero == null) {
            println("❌ Erro: Digite um número válido para a carga horária.")
            return null
        }
        return numero
    }

    private fun lerTextoObrigatorio(mensagem: String): String? {
        print(mensagem)
        val texto = scanner.nextLine().trim()
        if (texto.isBlank()) {
            println("❌ Erro: O campo não pode ficar em branco.")
            return null
        }
        return texto
    }

    private fun lerInteiroObrigatorio(mensagem: String): Int? {
        print(mensagem)
        val entrada = scanner.nextLine().trim()
        if (entrada.isBlank()) {
            println("❌ Erro: O campo numérico não pode ficar em branco.")
            return null
        }
        val numero = entrada.toIntOrNull()
        if (numero == null) {
            println("❌ Erro: Digite um número válido.")
            return null
        }
        return numero
    }

    private fun esperarEnter() {
        println("\nAperte Enter para continuar...")
        scanner.nextLine()
    }
}
