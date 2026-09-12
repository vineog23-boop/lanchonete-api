<h1 align="center">🍔 Lanchonete API</h1>
<p align="center">Pedidos, clientes e entregas em uma API REST de estudo.</p>
<p align="center">
  <img src="https://img.shields.io/badge/Java-21-2563EB?style=flat-square" alt="Java: 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.4.1-0F766E?style=flat-square" alt="Spring Boot: 3.4.1">
  <img src="https://img.shields.io/badge/Status-Em%20desenvolvimento-475569?style=flat-square" alt="Status: Em desenvolvimento">
</p>

<p align="center"><a href="#visão-geral">Visão geral</a> · <a href="#como-executar">Execução</a> · <a href="#próximos-passos">Próximos passos</a></p>

---

## Visão geral

API para organizar a operação de uma lanchonete: clientes, endereços, produtos, pedidos e entregadores. O projeto pratica modelagem relacional, contratos HTTP com DTOs e separação entre controller, service e repository.

**Configuração atual:** H2 em memória, porta **8081** e documentação Swagger. Os dados são recriados a cada inicialização; MySQL não está configurado nesta versão.

## Funcionalidades

| Recurso | Implementação atual |
| --- | --- |
| Clientes | Cadastro, listagem, consulta por UUID, atualização e exclusão. |
| Endereços | CRUD com vínculo ao cliente. |
| Produtos | CRUD e categorização por enum. |
| Pedidos | CRUD, itens e relacionamentos com cliente, endereço e entregador. |
| Entregadores | Cadastro, consulta, atualização e exclusão. |
| Contratos HTTP | DTOs de entrada e saída, Bean Validation e tratamento centralizado de erros. |

Os status disponíveis são `RECEBIDO`, `EM_PREPARO`, `SAIU_PARA_ENTREGA`, `ENTREGUE` e `CANCELADO`. As categorias de produto são `LANCHE`, `BAURU`, `DOCE`, `BEBIDA`, `COMBOS` e `OUTROS`. A existência desses enums não representa um fluxo automatizado de entrega.

## Tecnologias

| Tecnologia | Versão / papel |
| --- | --- |
| Java | 21 |
| Spring Boot | 3.4.1 |
| Spring Web, Data JPA e Validation | Versões gerenciadas pelo Spring Boot |
| H2 | Banco em memória |
| SpringDoc OpenAPI | 2.8.5 |
| Lombok | Redução de código repetitivo |
| Maven Wrapper | Build e execução |

## Como executar

Pré-requisito: **JDK 21**. O Maven Wrapper está incluído; não é necessário instalar um servidor de banco.

```bash
git clone https://github.com/vineog23-boop/lanchonete-api.git
cd lanchonete-api
bash ./mvnw spring-boot:run
```

No Windows, use `.\mvnw.cmd spring-boot:run`.

| Serviço | Endereço |
| --- | --- |
| API | http://localhost:8081 |
| Swagger UI | http://localhost:8081/swagger-ui.html |
| OpenAPI JSON | http://localhost:8081/v3/api-docs |
| Console H2 | http://localhost:8081/h2-console |

No console H2, use a URL JDBC `jdbc:h2:mem:lanchonete_db`, usuário `sa` e senha vazia. A configuração versionada usa `ddl-auto=create-drop`.

## Endpoints

Cada recurso abaixo oferece `POST` e `GET` na rota base, além de `GET`, `PUT` e `DELETE` em `/{id}`.

| Recurso | Rota base | Identificador |
| --- | --- | --- |
| Clientes | `/clientes` | UUID |
| Endereços | `/enderecos` | UUID |
| Produtos | `/produtos` | ID numérico |
| Pedidos | `/pedido` | UUID |
| Entregadores | `/entregadores` | ID numérico |

### Exemplo: cadastrar um cliente

Com a aplicação iniciada:

```bash
curl -X POST http://localhost:8081/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"Cliente Exemplo","celular":"16999990000","email":"cliente@example.com","dataNascimento":"1990-05-20"}'
```

O controller retorna `201 Created` com o DTO do cliente cadastrado. Consulte o Swagger para os contratos dos demais recursos.

## Organização do código

| Pacote | Responsabilidade |
| --- | --- |
| `controller` | Rotas, validação HTTP e códigos de resposta. |
| `services` | Operações de negócio e mapeamento dos dados. |
| `repository` | Persistência com Spring Data JPA. |
| `entity` | Modelo relacional, relacionamentos e enums. |
| `dto/request` e `dto/response` | Contratos de entrada e saída. |
| `exception` | Tratamento centralizado de falhas. |

## Testes

```bash
bash ./mvnw test
```

A suíte versionada contém o teste de contexto Spring. Ainda faltam testes específicos das regras de pedidos, validações e fluxos HTTP.

## Próximos passos

- Testar regras de pedidos, relacionamentos e respostas de erro.
- Preparar persistência durável e migrations antes de documentar um banco externo.
- Adicionar testes de integração e uma configuração reproduzível de infraestrutura.

## Autor

**Vinícius Oliveira** · [GitHub](https://github.com/vineog23-boop) · [LinkedIn](https://www.linkedin.com/in/vinícius-oliveira-1770b7306)
