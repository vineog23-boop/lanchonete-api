# 🍔 lanchonete-api

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2+-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-blue?style=for-the-badge&logo=spring)](https://spring.io/projects/spring-data-jpa)
[![Spring Validation](https://img.shields.io/badge/Spring_Validation-blue?style=for-the-badge&logo=spring)](https://spring.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Swagger](https://img.shields.io/badge/Swagger-SpringDoc_3.0.2-85EA2D?style=for-the-badge&logo=swagger)](https://springdoc.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.x-red?style=for-the-badge)](https://projectlombok.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9.14-blue?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)

---

## 📌 O que o projeto faz

A **lanchonete-api** é uma API REST para gerenciamento de pedidos, produtos, clientes, entregadores e endereços de entrega. Construída com Spring Boot e MySQL, o projeto aplica arquitetura em camadas com separação de responsabilidades entre Controller, Service e Repository, e modelagem relacional com JPA/Hibernate.

O objetivo é substituir uma operação que hoje vive no papel e na memória por um sistema com dados organizados, relacionamentos bem definidos e comportamento previsível.

---

## ✨ Funcionalidades

- ✅ CRUD completo de Clientes, Produtos, Pedidos e Entregadores
- ✅ Modelagem completa das entidades: Cliente, Produto, Pedido, Entregador, Endereço, ItemPedido
- ✅ Relacionamentos JPA mapeados (`@OneToMany`, `@ManyToOne`)
- ✅ Controle de status do pedido via enum (`RECEBIDO`, `EM_PREPARO`, `SAIU_PARA_ENTREGA`, `ENTREGUE`, `CANCELADO`)
- ✅ Categorização de produtos via enum (`SALGADO`, `DOCE`, `BEBIDA`, `COMBOS`, `OUTROS`)
- ✅ DTOs de Request e Response para todas as entidades
- ✅ Bean Validation nas entradas (`@Valid`)
- ✅ Tratamento centralizado de erros com `@RestControllerAdvice`
- ✅ Documentação interativa com Swagger UI

---

## 🛠 Tecnologias

| Tecnologia         | Versão   | Descrição                                         |
|--------------------|----------|---------------------------------------------------|
| Java               | 21       | Linguagem de programação                          |
| Spring Boot        | 3.2+     | Framework principal                               |
| Spring Web MVC     | 4.0.5    | Camada REST (controllers e serialização)          |
| Spring Data JPA    | 4.0.5    | Repositórios e acesso a dados via JPA             |
| Spring Validation  | 4.0.5    | Validação de entrada na camada de Controller      |
| Hibernate          | 6.x      | Implementação JPA e mapeamento objeto-relacional  |
| MySQL              | 8.0      | Banco de dados relacional                         |
| SpringDoc OpenAPI  | 3.0.2    | Documentação interativa via Swagger UI            |
| Lombok             | 1.18.x   | Redução de boilerplate em entidades               |
| Maven              | 3.9.14   | Gerenciamento de dependências e build             |

---

## 🏗 Arquitetura

O projeto segue arquitetura em camadas (MVC), com responsabilidades isoladas por pacote:

```
src/main/java/br/com/centrallanches/lanchonete_api/
├── controller/         # Recebe requisições HTTP, valida entrada e delega para o Service
│   ├── ClienteController.java
│   ├── PedidoController.java
│   ├── ProdutoController.java
│   └── EntregadorController.java
├── services/           # Regras de negócio, orquestração e mapeamento DTO ↔ entidade
│   ├── ClienteService.java
│   ├── PedidoService.java
│   ├── ProdutoService.java
│   ├── EntregadorService.java
│   └── EnderecoService.java
├── repository/         # Acesso ao banco via Spring Data JPA
│   ├── ClienteRepository.java
│   ├── PedidoRepository.java
│   ├── ProdutoRepository.java
│   ├── EntregadorRepository.java
│   ├── EnderecoRepository.java
│   └── ItemPedidoRepository.java
├── entity/             # Entidades JPA mapeadas para o banco MySQL
│   ├── Cliente.java
│   ├── Pedido.java
│   ├── Produto.java
│   ├── Entregador.java
│   ├── Endereco.java
│   ├── ItemPedido.java
│   └── enums/
│       ├── StatusPedido.java
│       └── CategoriaProduto.java
├── dto/
│   ├── request/        # Dados recebidos nas requisições (com validações)
│   └── response/       # Dados retornados nas respostas (sem expor entidade diretamente)
├── exception/          # Tratamento centralizado de erros
│   ├── GlobalExceptionHandler.java
│   ├── ErroPadrao.java
│   └── ApiErrorMessage.java
└── config/             # Configurações da aplicação (H2Console, etc.)
    └── H2ConsoleConfig.java
```

### Modelo de dados

```
Cliente (1) ──< Endereco    (N)
Cliente (1) ──< Pedido      (N)
Pedido  (1) ──< ItemPedido  (N)
Pedido  (N) >── Entregador  (1)
Pedido  (N) >── Endereco    (1)
```

---

## 📡 Endpoints

### Clientes — `/clientes`

| Método | Endpoint         | Descrição                  | Status |
|--------|------------------|----------------------------|--------|
| POST   | `/clientes`      | Cadastrar novo cliente     | 201    |
| GET    | `/clientes`      | Listar todos os clientes   | 200    |
| GET    | `/clientes/{id}` | Buscar cliente por UUID    | 200    |
| PUT    | `/clientes/{id}` | Atualizar cliente          | 200    |
| DELETE | `/clientes/{id}` | Remover cliente            | 204    |

### Produtos — `/produtos`

| Método | Endpoint          | Descrição                  | Status |
|--------|-------------------|----------------------------|--------|
| POST   | `/produtos`       | Cadastrar novo produto     | 201    |
| GET    | `/produtos`       | Listar todos os produtos   | 200    |
| GET    | `/produtos/{id}`  | Buscar produto por ID      | 200    |
| PUT    | `/produtos/{id}`  | Atualizar produto          | 200    |
| DELETE | `/produtos/{id}`  | Remover produto            | 204    |

### Pedidos — `/pedido`

| Método | Endpoint        | Descrição                  | Status |
|--------|-----------------|----------------------------|--------|
| POST   | `/pedido`       | Criar novo pedido          | 201    |
| GET    | `/pedido`       | Listar todos os pedidos    | 200    |
| GET    | `/pedido/{id}`  | Buscar pedido por UUID     | 200    |
| PUT    | `/pedido/{id}`  | Atualizar pedido           | 200    |
| DELETE | `/pedido/{id}`  | Remover pedido             | 204    |

### Entregadores — `/entregadores`

| Método | Endpoint               | Descrição                    | Status |
|--------|------------------------|------------------------------|--------|
| POST   | `/entregadores`        | Cadastrar novo entregador    | 201    |
| GET    | `/entregadores`        | Listar todos os entregadores | 200    |
| GET    | `/entregadores/{id}`   | Buscar entregador por ID     | 200    |
| PUT    | `/entregadores/{id}`   | Atualizar entregador         | 200    |
| DELETE | `/entregadores/{id}`   | Remover entregador           | 204    |

---

## 📚 Documentação interativa

Com a aplicação rodando, acesse:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

---

## ⚙️ Como rodar

### Pré-requisitos

- Java 21
- Maven 3.9+
- MySQL 8.0 rodando localmente

### Configuração do banco

Crie o banco de dados:

```sql
CREATE DATABASE lanchonete_db;
```

Configure `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lanchonete_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Executando

```bash
git clone https://github.com/vineog23-boop/lanchonete-api.git
cd lanchonete-api
mvn clean install
mvn spring-boot:run
```

A aplicação sobe em: **http://localhost:8080**

---

## ⚔️ Desafios enfrentados

**Modelagem antes do código:** A operação de lanchonete tem muita informação paralela — pedido, produto, entrega e cliente acontecendo ao mesmo tempo. Definir entidades e relacionamentos antes de escrever a camada de serviço foi a decisão que mais evitou retrabalho. Cada tabela tem uma responsabilidade, cada relacionamento tem um motivo.

**Relacionamentos bidirecionais com JPA:** Mapear `Cliente → Pedidos` e `Cliente → Enderecos` com `@OneToMany` exigiu entender `mappedBy`, `CascadeType.ALL` e `orphanRemoval`. A direção do relacionamento define quem é o lado dono da chave estrangeira no banco.

**Fetch lazy vs. eager:** Optamos por `FetchType.LAZY` nos relacionamentos de coleção para não carregar dados desnecessários a cada consulta. Endereços e pedidos de um cliente são carregados apenas quando explicitamente acessados.

**Enums como contrato:** Usar `@Enumerated(EnumType.STRING)` para `StatusPedido` e `CategoriaProduto` mantém o banco legível e protege contra bugs de reordenação que ocorrem com `EnumType.ORDINAL`.

**UUID como chave primária:** Clientes, Pedidos e Endereços usam `UUID` gerado pelo JPA. A decisão evita colisão de IDs em cenários onde os dados poderiam vir de múltiplas fontes.

**Tratamento centralizado de erros:** O `GlobalExceptionHandler` com `@RestControllerAdvice` garante que toda exceção retorne um `ErroPadrao` estruturado com mensagem, status HTTP e timestamp — sem vazar stacktrace para o cliente.

---

## 👨‍💻 Autor

**Vinícius Oliveira**  
[LinkedIn](https://linkedin.com/in/vinicius-oliveira-dev-) • [GitHub](https://github.com/vineog23-boop)
