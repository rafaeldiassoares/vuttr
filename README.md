# VUTTR (Very Useful Tools to Remember) 

Desafio Startaideia:

O desafio proposto foi a construção de uma API Rest com banco de dados intitulada VUTTR (Very Useful Tools to Remember), que seria uma aplicação 
simples para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.

Api rodando no Heroku: https://vuttr-spring-api.herokuapp.com/

Para testar as funcionalidades pode-se utilizar o Insomnia ou similares para enviar requisições para a API, existe uma documentação
para auxiliar na utilização que foi criada usando o Swagger: https://vuttr-spring-api.herokuapp.com/swagger-ui/index.html#/tag-resource

A API foi construída utilizando as seguintes tecnolgias:

- Spring Boot
- Postgres
- JPA
- Swagger
- JUnit (Testes)
- Docker (Conteinerização)

# Banco de dados

O banco de dados e suas tabelas são criados no momento da inicialização do projeto, para isso basta configurar o arquivo
`application.properties` do projeto com as credenciais de acesso ao banco de dados (Para funcionar no Docker pode se manter 
as configurações ja setadas no projeto). Foi adicionado um script no arquivo `vuttr.sql` que se encontra na raiz do projeto
para acompanhar o funcionamento da API.

# Executar a API

Foi realizado o deploy da aplicação no Heroku para facilitar a visualização da ferramenta ([VUTTR](https://vuttr-spring-api.herokuapp.com/)) em funcionamento mas é possível
executá-la utilizando o Docker, para isso é necessário ter o Docker instalado e clonar o repositório para sua máquina:

- Link do repositório no GitHub: `https://github.com/rafaeldiassoares/vuttr.git`
- Link do repositório no GitLab: `git@gitlab.com:rafaeldiassoares/vuttr.git`
- Fazer o Build com o Maven `mvn install -DskipTests`
- Levantar os containers Docker utilizando o Docker-compose.yml `docker-compose up`

# Recursos da API

* GET `/api/tools`
* GET `/api/tools?tag={tag}`
* GET `/api/tools/{id}`
* GET `/api/tags/`
* GET `/api/tags/{id}`
* POST `/api/tools`

  Exemplo de JSON para inserção:
  
```
{
    "title": "hotel",
    "link": "https://github.com/typicode/hotel",
    "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
    "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"]
}
  ```
 
* DELETE `/api/tools/{id}`
