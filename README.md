# 🌌 SpaceChat

**SpaceChat** é uma aplicação de **chat em tempo real com temática espacial**, desenvolvida com **Angular** no frontend e **Java Spring Boot** no backend. O sistema permite que usuários se cadastrem, validem seu e-mail e façam login para interagir em salas de bate-papo dinâmicas e intuitivas.

## 🚀 Funcionalidades

- 🧑‍🚀 Cadastro de usuários
- 📧 Validação de e-mail com link de ativação
- 🔐 Login com autenticação segura (JWT)
- 💬 Envio e recebimento de mensagens em tempo real
- 🪐 Interface moderna com temática espacial
- 📱 Layout responsivo

## 🛠️ Tecnologias Utilizadas

### 🔧 Backend (Java + Spring Boot)

- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- Envio de e-mails com JavaMailSender
- Banco de dados PostgreSQL
- Flyway (para migração do banco de dados)

### 🌐 Frontend (Angular)

- Angular 15+
- Angular Router
- Reactive Forms
- Angular Services e Interceptors
- Consumo de APIs RESTful
- CSS3 e responsividade

## 📦 Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Node.js 18+
- Angular CLI (`npm install -g @angular/cli`)
- PostgreSQL
- IDEs recomendadas: IntelliJ IDEA (backend) e VS Code (frontend)

---

### Backend

1. Acesse a pasta do projeto backend:

```bash
cd backend
````
2. Configure o arquivo application.properties com suas informações:
   
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/spacechat
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.mail.host=smtp.seuprovedor.com
spring.mail.port=587
spring.mail.username=seu_email
spring.mail.password=sua_senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

jwt.secret=umaChaveSecretaBemForte
````

3. Execute o projeto:

```bash
./mvnw spring-boot:run
```

### Frontend
1. Acesse a pasta do projeto frontend:
```bash
cd frontend
```
2. Instale as dependências:
```bash
npm install
 ```
3. Inicie a aplicação:
```bash
ng serve
```
4. Acesse: http://localhost:4200


## 🧑‍💻 Contribuindo

Contribuições são bem-vindas! Se você deseja sugerir melhorias, corrigir bugs ou adicionar novos recursos, sinta-se à vontade para:

- Fazer um **fork** do projeto
- Criar uma **branch** com sua feature
- Enviar um **Pull Request**


