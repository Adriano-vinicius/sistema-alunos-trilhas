# Evidências de Execução - Quinta-feira

## Funcionalidades demonstráveis

- Menu principal completo.
- Cadastro e listagem de aluno.
- Cadastro e listagem de curso.
- Cadastro e listagem de trilha.
- Associação de curso a trilha.
- Matrícula de aluno em trilha.
- Registro de progresso.
- Ranking por progresso.
- Relatório de alunos por trilha.
- Relatório de alunos sem matrícula.

## Fluxo integrado sugerido para print ou gravação

1. Executar o sistema.
2. Cadastrar um aluno ativo.
3. Listar alunos.
4. Listar trilhas e confirmar a trilha inicial.
5. Matricular o aluno na trilha.
6. Registrar progresso com quantidade válida.
7. Abrir relatórios.
8. Exibir ranking de progresso.
9. Exibir alunos por trilha.
10. Encerrar o sistema.

## Falha corrigida

Problema: a lógica de matrícula e progresso estava diretamente ligada ao aluno, usando `trail` e `cursosConcluidos` dentro da classe de aluno.

Correção: criação da classe `Matricula`, deixando o vínculo aluno-trilha responsável pelo status e pelo progresso.

Validação: o sistema agora verifica matrícula ativa antes de registrar progresso e exibe ranking com aluno, trilha, percentual e status.

## Revisão final - Sexta-feira

Na revisão final, foram conferidos os seguintes pontos:

- O sistema compila e executa no console.
- O menu principal exibe as opções obrigatórias.
- A função `main` apenas inicia a aplicação.
- O fluxo de menu foi separado na classe `ConsoleApp`.
- As regras de cadastro, matrícula, progresso, ranking e relatórios estão concentradas no `AcademicService`.
- As listas internas críticas estão protegidas.
- O projeto possui README, checklist, evidências, relatório curto e roteiro de apresentação.

## Evidências finais a anexar com prints reais

- Print do menu principal.
- Print de aluno cadastrado.
- Print de matrícula realizada.
- Print de progresso registrado.
- Print do ranking funcionando.
- Print do relatório de alunos por trilha.
- Print do GitHub com commits.
