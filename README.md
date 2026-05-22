# Sistema de Alunos e Trilhas

Projeto em Kotlin para console desenvolvido na Semana 02 do treinamento.

O sistema permite cadastrar alunos, cursos e trilhas, associar cursos às trilhas, matricular alunos, registrar progresso e exibir relatórios.

---

## Objetivo

Aplicar Programação Orientada a Objetos em Kotlin usando:

- Classes e objetos.
- Propriedades e métodos.
- Construtores com validação.
- Enum class.
- Listas de objetos.
- Encapsulamento.
- Separação entre domínio, serviço e console.

---

## Estrutura do projeto

```text
src/main/kotlin
├── app
│   ├── Main.kt
│   └── ConsoleApp.kt
│
├── model
│   ├── Course.kt
│   ├── Estudantes.kt
│   ├── Trilha.kt
│   └── Matricula.kt
│
└── service
    └── AcademicService.kt
```

---

## Principais classes

### `Estudantes`

Representa o aluno do sistema.

Possui:

- ID.
- Nome.
- E-mail.
- Situação.

---

### `Course`

Representa um curso.

Possui:

- ID.
- Título.
- Carga horária.
- Nível.
- Categoria.

---

### `Trilha`

Representa uma trilha de formação.

Possui:

- ID.
- Nome.
- Descrição.
- Status.
- Lista de cursos.

A lista de cursos é encapsulada, evitando alteração direta fora da classe.

---

### `Matricula`

Representa o vínculo entre aluno e trilha.

Controla:

- Aluno matriculado.
- Trilha escolhida.
- Cursos concluídos.
- Status da matrícula.
- Percentual de progresso.

---

### `AcademicService`

Classe responsável pelas regras principais do sistema.

Ela controla cadastros, buscas, matrículas, progresso, relatórios e ranking.

---

## Funcionalidades implementadas

- Cadastrar aluno.
- Listar alunos.
- Cadastrar curso.
- Listar cursos.
- Cadastrar trilha.
- Listar trilhas.
- Adicionar curso a uma trilha.
- Matricular aluno em trilha.
- Registrar progresso do aluno.
- Exibir relatórios.
- Exibir ranking por progresso.
- Listar alunos sem matrícula.

---

## Regras implementadas

- IDs devem ser maiores que zero.
- IDs não podem ser duplicados.
- Nome de aluno não pode ser vazio.
- E-mail precisa conter `@` e `.`.
- Curso precisa ter título.
- Carga horária deve ser maior que zero.
- Trilha precisa ter nome.
- Curso duplicado não pode ser adicionado na mesma trilha.
- Aluno inexistente não pode ser matriculado.
- Trilha inexistente não pode receber matrícula.
- Aluno inativo ou bloqueado não pode ser matriculado.
- Trilha planejada, concluída ou arquivada não aceita matrícula.
- O mesmo aluno não pode ser matriculado duas vezes na mesma trilha.
- Progresso não pode ser negativo.
- Progresso não pode ser maior que a quantidade de cursos da trilha.
- Trilha sem cursos não permite registro de progresso.
- Ranking ordena por percentual de progresso.
- Em caso de empate no ranking, o desempate é por nome.

---

## Organização do código

O projeto foi separado em três partes:

- `model`: classes de domínio.
- `service`: regras de negócio.
- `app`: menu e interação com o usuário.

A função `main` foi mantida simples, apenas iniciando a aplicação.

Essa organização evita deixar todas as regras dentro do menu e facilita uma futura adaptação para Android.

---

## Testes manuais realizados

Foram testados os seguintes casos:

- Cadastro válido de aluno, curso e trilha.
- Tentativa de cadastrar ID duplicado.
- Tentativa de cadastrar campos vazios.
- Tentativa de informar texto onde deveria ser número.
- Adição de curso em trilha.
- Bloqueio de curso duplicado na trilha.
- Matrícula de aluno em trilha.
- Bloqueio de matrícula duplicada.
- Registro de progresso.
- Bloqueio de progresso inválido.
- Ranking por progresso.
- Relatórios do sistema.
- Listagens vazias.
- Busca por ID inexistente.

---

## Checklist resumido

- [x] Sistema executa no console.
- [x] Menu principal funciona.
- [x] Alunos são cadastrados e listados.
- [x] Cursos são cadastrados e listados.
- [x] Trilhas são cadastradas e listadas.
- [x] Cursos são adicionados às trilhas.
- [x] Matrículas funcionam.
- [x] Progresso funciona.
- [x] Relatórios funcionam.
- [x] Ranking funciona.
- [x] Entradas inválidas são tratadas.
- [x] Estados vazios são tratados.
- [x] Código organizado em pacotes.

---

## Limitações conhecidas

- Os dados ficam apenas em memória.
- Ao fechar o programa, os dados cadastrados são perdidos.
- O sistema não possui interface gráfica.
- Os testes foram manuais, sem JUnit.

Essas limitações estão de acordo com o escopo atual do projeto, que é um sistema Kotlin de console.

---

## Próximos ajustes

Como melhoria futura, o projeto poderia receber:

- Testes automatizados.
- Edição de aluno, curso e trilha.
- Persistência em arquivo ou banco de dados.
- Adaptação para Android com ViewModel e Repository.

Esses itens são melhorias futuras e não impedem o funcionamento da versão atual.

---

## Conclusão

O Sistema de Alunos e Trilhas atende ao objetivo da atividade, aplicando Programação Orientada a Objetos em Kotlin no console.

A versão entregue possui cadastro, listagem, trilhas, matrícula, progresso, relatórios, ranking, validações, tratamento de erros e organização por pacotes.
