# Relatório Curto de Entrega

## Projeto
Sistema de Alunos e Trilhas

## O que foi implementado

- Cadastro e listagem de alunos, com validação de ID, nome, e-mail e situação.
- Cadastro e listagem de cursos, com validação de ID, título, carga horária, nível e categoria.
- Cadastro e listagem de trilhas, com descrição, status, quantidade de cursos e carga horária total.
- Associação de curso existente a trilha existente.
- Bloqueio de curso duplicado dentro da mesma trilha.
- Bloqueio de adição de cursos em trilhas concluídas ou arquivadas.
- Matrícula de aluno ativo em trilha ativa.
- Bloqueio de matrícula para aluno inativo ou bloqueado.
- Bloqueio de matrícula em trilha planejada, concluída ou arquivada.
- Bloqueio de matrícula duplicada do mesmo aluno na mesma trilha.
- Registro de progresso por matrícula.
- Bloqueio de progresso negativo, maior que o total de cursos ou em trilha sem cursos.
- Marcação da matrícula como concluída ao atingir 100%.
- Submenu de relatórios.
- Relatório de alunos cadastrados.
- Relatório de cursos cadastrados.
- Relatório de trilhas com carga horária total.
- Relatório de alunos matriculados por trilha.
- Ranking de progresso em ordem decrescente, com desempate por nome.
- Relatório de alunos sem matrícula.
- Tratamento de entradas vazias, entradas numéricas inválidas, buscas inexistentes e listas vazias.

## O que foi validado

- O sistema inicia pelo console e exibe o título correto.
- O menu principal possui as opções obrigatórias.
- O fluxo de cadastro, matrícula, progresso e ranking funciona de forma integrada.
- As principais regras de negócio foram validadas manualmente no console.
- O código evita `!!` em fluxo normal.
- O projeto separa domínio, serviço e fluxo de console.
- A função `main` ficou enxuta, apenas iniciando a aplicação.

## Limitações conhecidas

- Os dados ficam apenas em memória durante a execução.
- Não há persistência em arquivo ou banco de dados.
- Não há testes automatizados com JUnit nesta versão.
- Ainda não existe opção no menu para editar ou alterar status de aluno, curso ou trilha depois do cadastro.
- As evidências finais devem ser complementadas com prints reais do terminal ou uma gravação curta do sistema funcionando.

## Próximos ajustes

- Implementar testes automatizados para regras de domínio e serviço.
- Criar opção de edição de alunos, cursos e trilhas.
- Criar persistência local em arquivo ou banco futuramente.
- Separar relatórios em uma classe de serviço própria, caso o projeto cresça.
- Migrar futuramente a arquitetura para um app Android com ViewModel, Repository e telas em Jetpack Compose.

## Lacuna individual registrada

A principal lacuna individual identificada foi a necessidade de melhorar a separação entre menu, serviço e domínio desde o início do projeto. No começo, parte do fluxo estava concentrada no `main`. A correção foi criar a classe `ConsoleApp`, deixando o `Main.kt` apenas como ponto de entrada e concentrando as regras coletivas no `AcademicService`.
