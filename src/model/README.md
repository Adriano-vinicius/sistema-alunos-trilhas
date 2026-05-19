# Sistema de Alunos e Trilhas - Semana 02

## 📝 Extração do Briefing
Este projeto inicial foi criado para gerenciar alunos e suas respectivas trilhas de cursos técnicos no console.

### Entidades do Sistema:
- **Student (Aluno):** Guarda os dados do estudante (ID, Nome, E-mail) e a Trilha em que ele está matriculado.
- **Course (Curso):** Representa as matérias isoladas (ID, Título, Carga Horária).
- **Trail (Trilha):** Agrupa vários cursos e calcula o tempo total de horas.

### Regras de Negócio Técnicas:
1. IDs de Alunos, Cursos e Trilhas precisam ser números maiores que zero.
2. Nomes e títulos não podem ser deixados em branco.
3. O e-mail do aluno obrigatoriamente precisa conter o símbolo '@'.
4. Não é permitido adicionar o mesmo curso duas vezes na mesma trilha.

## ✅ Checklist de Entregáveis (Hoje)
- [x] Criar estrutura do projeto Kotlin no Console
- [x] Extrair Entidades e Regras do Briefing (Este documento)
- [x] Criar classes Student, Course e Trail com validações
- [x] Criar repositório Git e fazer o primeiro commit