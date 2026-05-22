# Checklist de Testes Manuais

## Inicialização e menu

- [x] Sistema inicia sem erro.
- [x] Título “Sistema de Alunos e Trilhas” aparece.
- [x] Menu principal possui opções obrigatórias.
- [x] Opção inválida exibe mensagem adequada.
- [x] Opção sair encerra corretamente.
- [x] Após operação, sistema retorna ao menu.

## Aluno

- [x] Cadastrar aluno válido.
- [x] Impedir aluno com ID duplicado.
- [x] Impedir aluno com nome vazio.
- [x] Impedir aluno com nome menor que 3 caracteres.
- [x] Impedir e-mail sem @.
- [x] Impedir e-mail sem ponto.
- [x] Listar alunos cadastrados.
- [x] Exibir estado vazio.
- [x] Validar situação por enum.
- [x] Impedir matrícula de aluno inativo ou bloqueado.

## Curso

- [x] Cadastrar curso válido.
- [x] Impedir curso com ID duplicado.
- [x] Impedir curso com título vazio.
- [x] Impedir carga horária zero.
- [x] Impedir carga horária negativa.
- [x] Listar cursos cadastrados.
- [x] Exibir estado vazio.
- [x] Validar nível por enum.
- [x] Validar categoria por enum.

## Trilha

- [x] Cadastrar trilha válida.
- [x] Impedir trilha com ID duplicado.
- [x] Impedir trilha com nome vazio.
- [x] Listar trilhas.
- [x] Exibir estado vazio.
- [x] Exibir quantidade de cursos.
- [x] Exibir carga horária total.
- [x] Validar status por enum.

## Associação curso-trilha

- [x] Adicionar curso existente a trilha existente.
- [x] Impedir trilha inexistente.
- [x] Impedir curso inexistente.
- [x] Impedir curso duplicado na mesma trilha.
- [x] Impedir curso em trilha concluída.
- [x] Impedir curso em trilha arquivada.
- [x] Confirmar atualização da quantidade de cursos.
- [x] Confirmar atualização da carga horária total.

## Matrícula

- [x] Matricular aluno ativo em trilha ativa.
- [x] Impedir aluno inexistente.
- [x] Impedir trilha inexistente.
- [x] Impedir aluno inativo.
- [x] Impedir aluno bloqueado.
- [x] Impedir trilha planejada.
- [x] Impedir trilha concluída.
- [x] Impedir trilha arquivada.
- [x] Impedir matrícula duplicada.
- [x] Listar alunos por trilha.

## Progresso

- [x] Registrar progresso válido.
- [x] Impedir progresso sem matrícula ativa.
- [x] Impedir progresso em trilha sem cursos.
- [x] Impedir quantidade negativa.
- [x] Impedir quantidade maior que total de cursos.
- [x] Calcular percentual corretamente.
- [x] Marcar matrícula como concluída em 100%.
- [x] Exibir mensagem correta após registro.

## Relatórios

- [x] Exibir alunos cadastrados.
- [x] Exibir cursos cadastrados.
- [x] Exibir trilhas com carga horária total.
- [x] Exibir alunos por trilha.
- [x] Exibir mensagem para trilha sem alunos.
- [x] Exibir ranking em ordem decrescente.
- [x] Validar desempate por nome.
- [x] Exibir alunos sem matrícula.
- [x] Exibir mensagem quando todos possuem matrícula.
- [x] Voltar do submenu ao menu principal.

## Estabilidade

- [x] Texto onde espera número não quebra o sistema.
- [x] Entrada vazia não quebra o sistema.
- [x] Busca inexistente mostra erro controlado.
- [x] Listas vazias são tratadas.
- [x] Operações repetidas não geram crash.
- [x] Encerramento após várias operações funciona.
- [x] Não há uso de `!!` no fluxo normal.
