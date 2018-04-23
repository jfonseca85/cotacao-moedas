###Shangrila - Cotação de Moedas
###Spring MicroServices

Neste projeto foi utilizada a arquitetura baseada em microserviços.

Introdução
O desenvolvimento de serviços Web RESTful foi feito uma combinação de Spring Boot, Spring Web MVC, Spring Web Services e JPA torna ainda mais empolgante trabalhar com essas ferramentas. E é ainda mais criar microsserviços.

Arquiteturas estão se movendo em direção a microservices.

Os serviços Web RESTful são o primeiro passo para o desenvolvimento de grandes microsserviços. O Spring Boot, em combinação com o Spring Web MVC (também chamado de Spring REST), facilita o desenvolvimento de serviços da Web RESTful.

Neste projeto eu utilizei os fundamentos dos serviços RestFul. Implementando esses recursos com vários recursos - controle de versão, tratamento de exceções, documentação (Swagger), autenticação básica (Spring Security), filtragem e HATEOAS. Procurando sem utilizar as melhores práticas na criação de serviços da Web RESTful.

Nesta utilizei Spring (Gerenciamento de Dependência), Spring MVC (ou Spring REST), Spring Boot, Spring Security (Autenticação e Autorização), Spring Boot Actuador (Monitoring), Swagger (Documentation), Maven ( gerenciamento de dependências), Eclipse (IDE), Postman (Cliente de Serviços REST) ​​e Servidor da Web Incorporado Tomcat e MongoDB.

Utilizei o básico sobre microsserviços. Implementando microsserviços usando o Spring Cloud.

###O que eu fiz

Desenvolvi e projetei serviços da Web RESTfull
Configuração Centralizada do Microservice com o Spring Cloud Config Server
Manipulação de exceções, Validação, HATEOAS e filtragem para serviços Web RESTful.
O balanceamento de carga do lado do cliente (Ribbon), o dimensionamento dinâmico (Eureka Naming Server) e um gateway de API (Zuul)
Implementei o rastreamento distribuído para microsserviços com o Spring Cloud Sleuth e o 

###Zipkin
Tolerância a falhas para microsserviços com Zipkin
Monitoramento dos serviços RESTful com o Spring Boot Actuador
Documentação os Serviços Web RESTful com o Swagger
Sempre utilizando as melhores práticas no design de serviços da web RESTful
Usando o Spring Cloud Bus para trocar mensagens sobre atualizações de configuração
Procurei simplificar a comunicação com outros microsserviços usando o Feign REST Client
Ferramentas Utilizadas

## Installing Tools
- Eclipse & Embedded Maven
- PostMan
- Git Client - https://git-scm.com/
- Rabbit MQ - https://www.rabbitmq.com/download.html


### Installing Eclipse & Embedded Maven
- Installation Video : https://www.youtube.com/playlist?list=PLBBog2r6uMCSmMVTW_QmDLyASBvovyAO3
- GIT Repository For Installation : https://github.com/in28minutes/getting-started-in-5-steps
- PDF : https://github.com/in28minutes/SpringIn28Minutes/blob/master/InstallationGuide-JavaEclipseAndMaven_v2.pdf

### Instalando Rabbit MQ

#### Windows
- https://www.rabbitmq.com/install-windows.html
- https://www.rabbitmq.com/which-erlang.html
- http://www.erlang.org/downloads
- Video - https://www.youtube.com/watch?v=gKzKUmtOwR4

#### Mac
- https://www.rabbitmq.com/install-homebrew.html

## Running Examples
- Download the zip or clone do Repositório Git.
- Unzip o arquivo zip(Caso voce tenha feito Download)
- Abra o Command Prompt e mude o diretorio(cd) para a pasta que contém o pom.xml
- Abra o  Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Selecione o projeto correto
- Procure o arquivo do Spring Boot Application (Busque por @SpringBootApplication)
- Clique botão direito e execute Run as Java Application

## Ports

|     Application       |     Port          |
| ------------- | ------------- |
| Limits Service | 8080, 8081, ... |
| Spring Cloud Config Server | 8888 |
|  |  |
| Currency Exchange Service | 8000, 8001, 8002, ..  |
| Currency Conversion Update | 8200, 8201, 8202, ... |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |


## URLs

|     Application       |     URL          |
| ------------- | ------------- |
| Limits Service | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh|
| Spring Cloud Config Server| http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev |
| Currency-conversion-update - Direct Call| http://localhost:8200/valueSet/batchUpload/20141120|
| Currency Exchange Service | http://localhost:8000/currency-converter/from/AFN/to/ETB/quantity/100?quotation=19/04/2018 http://localhost:8001/currency-converter/from/AFN/to/ETB/quantity/100?quotation=19/04/2018|
| Eureka | http://localhost:8761/|
| Zipkin | http://localhost:9411/zipkin/ |
| Spring Cloud Bus Refresh | http://localhost:8080/bus/refresh |

Neste projeto procurei trabalhar solucionando os requisitos funcionais e não funcionais.

####Requisitos Funcionais 

##RF001 - Caso os parâmetros to e from não forem válidos lançar uma exception;

##RF002 - Caso o valor for menor que zero a ali deverá lançar uma exception;

##RF003 - Para dias em que não há trabalho ( Sábado e Domingo, ignorando feriados). Pegar o primeiro dia antecedente de trabalho. Se a cotação do dia precedente não for avaliada a ali deverá lançar um exception.

##RF004 - Caso a data da cotação não for avaliada a ali deverá lançar uma exception.

##RF005 - O retorno deverá ser arredondado em duas casas decimais;

##RF006 - Para calcular a conversão deverá utilizar o valor de compra.

##RF007 - Schedule para atualizar o repositório local com as cotações.


####Requisitos não- Funcionais 

##RNF001 - Disponibilizar a funcionalidade através de uma api externa;

##RNF002 - O resultado deverá em formato o json;

##RNF003 - Utilizar um Banco de Dados No-SQL (MongoDB);

##RNF004 - Arquitetura deve está preparada para receber centenas de requisições por segundo;

##RNF005 - A Aplicação deverá prever falhas de integração entre as APIs, não deixando o client sem nenhuma resposta.


####Cenário

Dentre os microservices do projeto dois são responsáveis por implementar as regras de negócios da Aplicação. 

###spring-batch-csv-to-mongo - 
Este projeto é a atualização do repositório local, indo pegar as informações no DataSource do Banco disponibilizado http://www4.bcb.gov.br/Download/fechamento/{stringDateBussinesUpdate}.csv

Onde o {stringDateBussinesUpdate} é a data no formato yyyyMMdd (20141120)


Exemplo para atualizar o repositório Local com uma date específica

http://localhost:8200/valueSet/batchUpload/20141120

###currency-exchange-service 
- Este projeto é responsável por calcular a conversão entre moedas, ele faz a busca no repositório local caso não encontre faz a solicitação para atualizar o Banco de Dados Local, depois realizar nova busca caso não encontre lança uma exception, falando que a cotação não é avaliada para aquele dia.


