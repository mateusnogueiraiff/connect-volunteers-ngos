# Connect Volunteers & NGOs

## Objetivo do Projeto
O **Connect Volunteers & NGOs** é uma aplicação web voltada para conectar voluntários a Organizações Não Governamentais (ONGs). O sistema permite que voluntários se cadastrem, visualizem vagas disponíveis, inscrevam-se em oportunidades e acompanhem suas inscrições. Por outro lado, representantes das ONGs podem cadastrar novas vagas, gerenciar inscrições e visualizar detalhes dos voluntários inscritos. Administradores possuem acesso completo de gerenciamento.

---

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.5.4** (Spring Web, Spring Data JPA, Spring Boot Starter Validation)
- **Thymeleaf** (para renderização de páginas HTML)
- **Banco de Dados H2** (em memória para desenvolvimento)
- **Maven** (gerenciador de dependências)
- **HTML, CSS e JavaScript** (para a interface do usuário)

---

## Funcionalidades Principais

### Interface Web (MVC)
1. CRUD Completo para Entidades (Gerenciamento completo: Criar, Ler, Atualizar e Excluir para Voluntários, Representantes, Administradores, ONGs e Vagas diretamente pela interface)
2. Sistema de Login: Autenticação de usuários com diferentes perfis (Voluntário, Representante, Administrador)
3. Inscrição em Vagas: Voluntários podem se inscrever e cancelar suas inscrições em vagas.
4. Visualização de Inscrições: Representantes podem ver a lista de voluntários inscritos em suas vagas, e voluntários podem ver suas próprias inscrições.
5. Tratamento de Erros: Páginas de erro personalizadas para uma melhor experiência do usuário.

### API RESTful
1. Endpoints CRUD para todos os Recursos: API completa com operações de Criar, Ler, Atualizar e Excluir para todas as principais entidades (Voluntario, Representante, Administrador, ONG, Vaga, Inscricao).
2. Padrões REST: Utilização correta de verbos HTTP, URLs baseadas em recursos e versionamento (/api/v1/...).
3. Tratamento de Exceções Padronizado: Respostas de erro para a API seguem o padrão ProblemDetail (RFC 7807), garantindo uma comunicação clara e padronizada para os clientes da API.
4. Documentação com Swagger: A API é autodocumentada com Swagger, fornecendo uma interface interativa para explorar e testar os endpoints.

---

## Como Executar

Siga os passos abaixo para executar o projeto em sua máquina local:

### Pré-requisitos
- **Java 17** instalado e configurado no PATH
- **Maven** instalado e configurado no PATH
- Um editor ou IDE de sua preferência (IntelliJ, Eclipse, VS Code)

---

### Passo 1: Clonar o repositório
```bash
git clone https://github.com/seu-usuario/connect-volunteers-ngos.git
cd connect-volunteers-ngos
```

### Passo 2: Configurar dependências
```bash
mvn clean install
```

### Passo 3: Rodar a aplicação
Via IDE:
- Abra o projeto na sua IDE preferida.
- Execute a classe principal "ConnectVolunteersNgosApplication".

### Passo 4: Acessar a aplicação
Após iniciar a aplicação, acesse no navegador:
localhost:8080/

### Passo 5: Banco de Dados H2
O banco H2 é utilizado para desenvolvimento e está configurado em memória. Para acessar a interface web do H2:

1. Acesse "http://localhost:8080/h2-console"
2. Configurações:
- Driver Class: org.h2.Driver
- JDBC URL: "jdbc:h2:mem:connect-volunteers-ngos"
- Username: "sa"
- Password: "password"

### Passo 6: Acesso à API e Documentação
Documentação da API (Swagger UI)

A documentação interativa da API REST está disponível e pode ser acessada enquanto a aplicação está em execução.

1. URL da Documentação: http://localhost:8080/swagger-ui.html

## Observações

- As páginas de cadastro e listagem de usuários e vagas foram estilizadas com CSS unificado.
- O sistema é responsivo e funciona em dispositivos móveis.
- As mensagens de sucesso e erro são exibidas dinamicamente via Thymeleaf.

## Autores

[@mateusnogueiraiff](https://www.github.com/mateusnogueiraiff)

[@romiltonmanhaesiff](https://www.github.com/romiltonmanhaesiff)
