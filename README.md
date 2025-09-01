# Connect Volunteers & NGOs

## Objetivo do Projeto
O **Connect Volunteers & NGOs** é uma aplicação web voltada para conectar voluntários a Organizações Não Governamentais (ONGs). O sistema permite que voluntários se cadastrem, visualizem vagas disponíveis, inscrevam-se em oportunidades e acompanhem suas inscrições. Por outro lado, representantes das ONGs podem cadastrar novas vagas, gerenciar inscrições e visualizar detalhes dos voluntários inscritos. Administradores possuem acesso completo de gerenciamento.

---

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.5.4**
- **Thymeleaf** (para renderização de páginas HTML)
- **Banco de Dados H2** (em memória para desenvolvimento)
- **Maven** (gerenciador de dependências)
- **HTML, CSS e JavaScript** (para a interface do usuário)

---

## Funcionalidades Principais
1. Cadastro e login de usuários (Voluntários, Representantes e Administradores)
2. Cadastro e gerenciamento de vagas
3. Visualização de vagas disponíveis
4. Inscrição de voluntários em vagas
5. Consulta de inscrições por vaga
6. Perfis de usuários com detalhes completos
7. Mensagens de sucesso e erro ao realizar operações

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

## Observações

- As páginas de cadastro e listagem de usuários e vagas foram estilizadas com CSS unificado.
- O sistema é responsivo e funciona em dispositivos móveis.
- As mensagens de sucesso e erro são exibidas dinamicamente via Thymeleaf.

## Autores

[@mateusnogueiraiff](https://www.github.com/mateusnogueiraiff)

[@romiltonmanhaesiff](https://www.github.com/romiltonmanhaesiff)
