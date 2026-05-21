# Sistema de Alunos e Trilhas

Este projeto é a estrutura inicial da Semana 02 para o treinamento de Desenvolvimento de Aplicativos Móveis.

## 📋 Extração do Briefing (Requisitos do Sistema)

### 1. Entidades (O que o sistema possui)
* **Student (Aluno):** Representa o estudante, contendo identificador único (ID), nome, e-mail, a trilha em que está matriculado e a quantidade de cursos concluídos.
* **Course (Curso):** Representa as matérias ou módulos, contendo ID, título, carga horária, nível e categoria.
* **Trail (Trilha):** Representa o caminho de aprendizado, contendo ID, nome da trilha e uma lista protegida de cursos que fazem parte dela.

### 2. Regras de Negócio (O que o sistema exige)
* **IDs Positivos:** Os identificadores de Alunos, Cursos e Trilhas não podem ser zero nem números negativos.
* **Textos Preenchidos:** Nomes e e-mails não podem ser deixados em branco.
* **Carga Horária Válida:** Um curso deve ter uma carga horária maior que zero horas.
* **E-mail Válido:** O e-mail do aluno precisa conter o caractere `@`.
* **Bloqueio de Duplicidade:** Não é permitido adicionar o mesmo curso mais de uma vez na mesma trilha.
* **Tratamento de Buscas:** Tentar buscar ou associar uma Trilha ou Curso que não existe exibirá uma mensagem de erro controlada.
* **Cálculo de Ranking (Novo):** Ordenação decrescente por percentual de cursos concluídos da trilha.
* **Regra de Desempate (Novo):** Em caso de empate no progresso, o desempate é feito por ordem alfabética do nome do aluno.

### 3. Entregáveis da Semana
* Repositório Git configurado.
* Criação das classes principais (`Student`, `Course`, `Trail`).
* Criação da classe de serviço (`AcademicService`) para gerenciar os dados.
* Este documento de explicação preenchido.

## 🛠️ Checklist de Testes Iniciais e Casos de Borda
- [x] Criar classe Course com validação de carga horária e ID.
- [x] Criar classe Trail com validação de ID e nome.
- [x] Criar classe Student com validação de e-mail e ID.
- [x] Criar classe AcademicService para gerenciar as listas e buscas.
- [x] Testar bloqueio de cursos duplicados na trilha.
- [x] Testar aviso de erro para IDs de cursos ou trilhas que não existem.
- [x] **Teste Caso de Borda (Progresso Máximo):** Impede o registro de progresso além do número de cursos que a trilha possui.
- [x] **Teste Caso de Borda (Divisão por zero):** Garante que alunos matriculados em trilhas sem cursos fiquem estáveis em 0% de progresso sem quebrar o app.
- [x] **Teste de Desempate do Ranking:** Validado que alunos com o mesmo percentual desempatam corretamente por ordem alfabética.
- [x] Executar o arquivo Main para validar o funcionamento no console.

## 🛡️ Tratamento de Erros e Robustez (UX)

Durante a fase de testes e validação do sistema, foram identificadas e corrigidas quatro falhas críticas de experiência do utilizador (UX) e estabilidade do console:

1. **Confirmação de Leitura ("Aperte Enter para continuar"):**
    * **Problema:** O programa executava as ações e cuspia o menu gigante novamente de forma instantânea. O utilizador e o avaliador não tinham tempo de ler se a operação tinha dado certo ou qual era a mensagem de sucesso/erro.
    * **Solução:** Criação da função utilitária `esperarEnter(scanner)` aplicada ao final de cada opção do menu. O sistema agora faz uma pausa estratégica e só avança quando o utilizador pressiona a tecla `Enter`. Também foi adicionada a confirmação "Aperte enter para sair" na opção 0.

2. **Proteção Contra Entradas em Branco (Campos Vazios):**
    * **Problema:** O utilizador podia simplesmente pressionar `Enter` sem digitar nenhuma informação, deixando dados como nome, ID ou título vazios.
    * **Solução:** Mudança de toda a captura de dados para `scanner.nextLine()`. Adicionada validação com `.isBlank()` que rejeita a operação na hora, exibindo uma mensagem de erro e impedindo dados inconsistentes no sistema.

3. **Carga Horária Tolerante a Sufixos ("30h"):**
    * **Problema:** Ao digitar a carga horária de um curso incluindo a letra "h" (ex: `30h`), o método tradicional `nextInt()` disparava a exceção `InputMismatchException` e encerrava o programa abruptamente.
    * **Solução:** Implementação de tratamento de string através de `.endsWith("h")` e `.substring()`, que remove de forma inteligente a letra "h" ou "H", isolando o número bruto e convertendo-o com segurança via `.toIntOrNull()`.

4. **Sugestão Dinâmica de Próximo ID Livre:**
    * **Problema:** Usar o tamanho atual da lista (`size`) para guiar o utilizador na criação do ID gerava erros de duplicidade se o utilizador pulasse um identificador (ex: criar os IDs 1, 2, 3 e depois pular para o 5). O sistema sugeriria incorretamente o ID 4 na próxima vez, gerando conflito.
    * **Solução:** Desenvolvimento da função `obterProximoIdCursoSugerido()` no `AcademicService`, que varre a lista, descobre qual é o maior ID cadastrado de facto e sugere sempre o próximo ID real disponível (`maiorId + 1`).