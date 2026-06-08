# Introdução
Este projeto implementa a solução para o desafio de validação de senha.

## Descrição
A ideia do desafio é implementar uma API que valida uma senha seguindo as seguintes premissas:

- nove ou mais caracteres;
- ao menos 1 dígito;
- ao menos 1 letra minúscula;
- ao menos 1 letra maiúscula;
- ao menos 1 caractere especial;
- considerar como especiais os caracteres `!@#$%^&*()-+`;
- não possuir caracteres repetidos dentro do conjunto;
- não possuir espaços em branco.

## Tecnologias utilizadas

- Java 21
- Kotlin 2.2.21
- Spring Boot 4.0.6
- Maven
- JUnit 5
- MockK

## Como rodar o projeto

### Pré-requisitos

- Java 21 instalado;
- Maven disponível no ambiente, ou uso do wrapper `./mvnw`.

### Rodando localmente

```bash
./mvnw spring-boot:run
```

O serviço sobe por padrão em `http://localhost:8080`.

### Executando os testes

```bash
./mvnw test
```

### Alternativa com Docker

O desafio não exige Docker para avaliação, mas o projeto inclui um `Dockerfile` como alternativa de execução local:

```bash
docker build -t auth-service .
docker run --rm -p 8080:8080 auth-service
```

## Documentação

### Contrato da API

O serviço expõe um único endpoint para validação de senha.

#### `POST /api/v1/password/validate`

Recebe um JSON com a senha a ser validada e retorna um JSON indicando se ela é válida.

**Request**

```json
{
  "password": "AbTp9!fok"
}
```

**Response `200 OK` - senha válida**

```json
{
  "valid": true
}
```

**Response `200 OK` - senha inválida**

```json
{
  "valid": false
}
```

**Response `400 Bad Request` - requisição inválida**

Pode ocorrer quando o corpo da requisição estiver ausente, malformado ou incompatível com o contrato esperado.

#### Regras da resposta

- requisições válidas retornam `200 OK`;
- o campo `valid` retorna `true` quando a senha atende a todas as regras;
- o campo `valid` retorna `false` quando pelo menos uma regra é violada;
- o contrato atual não devolve a lista de violações, apenas o resultado da validação.

### Arquitetura

Tendo em conta os critérios de avaliação do desafio, como SOLID e clean code, foi adotada uma arquitetura em camadas com domínio isolado da camada web.

A divisão principal fica em três áreas:

- `domain`: contém o núcleo da regra de negócio, com o `PasswordValidator`, o resultado da validação, as violações e as regras individuais;
- `infra.config`: concentra a composição dos beans e a configuração das regras usadas pela aplicação;
- `infra.web`: expõe a API REST responsável por receber a requisição e devolver a resposta;

### Escolhas de design

A validação foi modelada tendo em mente o padrão strategy, contando com um conjunto de regras independentes, cada uma responsável por uma preocupação específica. O `PasswordValidator` apenas orquestra a execução dessas regras e consolida o resultado.

Esse desenho traz alguns benefícios:

- cada regra possui responsabilidade única;
- novas regras podem ser adicionadas sem impacto nas existentes;
- o comportamento fica mais fácil de testar, explicar e evoluir;

Outro ponto importante é que a solução valida todas as regras antes de falhar. Embora o contrato atual exponha apenas o booleano exigido pelo desafio, manter as violações no domínio deixa a aplicação preparada para evoluir para um retorno detalhado sem alterar o núcleo da validação.

### Possíveis evoluções

A modelagem atual permite algumas evoluções sem mudança estrutural relevante:

- uso de policies para combinar regras diferentes por perfil de usuário;
- associação de policies a tipos de usuário, como cliente, admin ou parceiro;
- configuração externa das regras por arquivo, banco de dados ou feature flag;
- modo fail-fast em contextos onde a primeira violação já seja suficiente;
- retorno detalhado das violações, caso a API precise evoluir além do booleano atual.
