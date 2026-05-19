# Sistema de Alunos e Trilhas

Este projeto é a estrutura inicial da Semana 02 para o treinamento de Desenvolvimento de Aplicativos Móveis.

## 📋 Extração do Briefing (Requisitos do Sistema)

### 1. Entidades (O que o sistema possui)
* **Student (Aluno):** Representa o estudante, contendo identificador único (ID), nome, e-mail e a trilha de estudos em que está matriculado.
* **Course (Curso):** Representa as matérias ou módulos, contendo ID, título e a carga horária.
* **Trail (Trilha):** Representa o caminho de aprendizado, contendo ID, nome da trilha e uma lista de cursos que fazem parte dela.

### 2. Regras de Negócio (O que o sistema exige)
* **IDs Positivos:** Os identificadores de Alunos, Cursos e Trilhas não podem ser zero nem números negativos.
* **Textos Preenchidos:** Nomes e e-mails não podem ser deixados em branco.
* **Carga Horária Válida:** Um curso deve ter uma carga horária maior que zero horas.
* **E-mail Válido:** O e-mail do aluno precisa conter os caracteres `@` e `.`.

### 3. Entregáveis da Semana
* Repositório Git configurado.
* Criação das classes principais (`Student`, `Course`, `Trail`).
* Este documento de explicação preenchido.

## 🛠️ Checklist de Testes Iniciais
- [x] Criar classe Course com validação de carga horária e ID.
- [x] Criar classe Trail com validação de ID e nome.
- [x] Criar classe Student com validação de e-mail e ID.
- [x] Executar o arquivo Main para validar o funcionamento no console.