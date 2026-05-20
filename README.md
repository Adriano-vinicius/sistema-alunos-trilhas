# Sistema de Alunos e Trilhas

Este projeto é a estrutura inicial da Semana 02 para o treinamento de Desenvolvimento de Aplicativos Móveis.

## 📋 Extração do Briefing (Requisitos do Sistema)

### 1. Entidades (O que o sistema possui)
* **Student (Aluno):** Representa o estudante, contendo identificador único (ID), nome, e-mail e a trilha de estudos em que está matriculado.
* **Course (Curso):** Representa as matérias ou módulos, contendo ID, título, carga horária, nível e categoria.
* **Trail (Trilha):** Representa o caminho de aprendizado, contendo ID, nome da trilha e uma lista protegida de cursos que fazem parte dela.

### 2. Regras de Negócio (O que o sistema exige)
* **IDs Positivos:** Os identificadores de Alunos, Cursos e Trilhas não podem ser zero nem números negativos.
* **Textos Preenchidos:** Nomes e e-mails não podem ser deixados em branco.
* **Carga Horária Válida:** Um curso deve ter uma carga horária maior que zero horas.
* **E-mail Válido:** O e-mail do aluno precisa conter o caractere `@`.
* **Bloqueio de Duplicidade (Novo):** Não é permitido adicionar o mesmo curso mais de uma vez na mesma trilha.
* **Tratamento de Buscas (Novo):** Tentar buscar ou associar uma Trilha ou Curso que não existe exibirá uma mensagem de erro em vez de quebrar o sistema.

### 3. Entregáveis da Semana
* Repositório Git configurado.
* Criação das classes principais (`Student`, `Course`, `Trail`).
* Criação da classe de serviço (`AcademicService`) para gerenciar os dados.
* Este documento de explicação preenchido.

## 🛠️ Checklist de Testes Iniciais
- [x] Criar classe Course com validação de carga horária e ID.
- [x] Criar classe Trail com validação de ID e nome.
- [x] Criar classe Student com validação de e-mail e ID.
- [x] Criar classe AcademicService para gerenciar as listas e buscas (Quarta-feira).
- [x] Testar bloqueio de cursos duplicados na trilha (Quarta-feira).
- [x] Testar aviso de erro para IDs de cursos ou trilhas que não existem (Quarta-feira).
- [x] Executar o arquivo Main para validar o funcionamento no console.