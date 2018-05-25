# banksLip-api

BanksLip-api : É um serviço de Pagamento, detalhamento de boleto bancário.

Para desenvolvimento local são necessários os seguintes itens:

* Java 8
* maven >= 3.5.x
* docker

 Porta da aplicação:
  * Se for rodar via docker a porta é **8090** 
  * Se for rodar a aplicação via aplicativos (eclipse,intelliJ) a porta á **8080**	

```
build:
    # build do projeto, bem como das imagens docker
    	- make docker-run

run:
    # levanta o banco,roda os testes unitários e inicia a aplicação (todos via docker)
```

# Banco de Dados

Esta aplicação usa um banco em memória(H2).

Para ver o painel do h2 é só acessar este link: http://localhost:8090/h2/


* Drive class: org.h2.Driver
* JDBC URL: jdbc:h2:~/test
* user: hyan
* password: hyan


## Swagger

Este projeto utiliza o swagger para documentar suas APIs. O mesmo pode ser acessado nos links abaixo:

**Ambiente Local**

http://localhost:8090/swagger-ui.html#/

Existem quatro serviços na api banksLip que são elas:

#Criação de um boleto bancário

 ex do request - POST :
  
 URL :  localhost:8090/rest/bankslip
 
 ```
 {
  "dueDate": "2018-05-25",
  "totalInCents": 0,
  "customer": "string",
  "status": "string"
}

```

#Detalhamento de um determinado boleto bancário

 ex do request - GET
 
  URL :  http://localhost:8090/rest/bankslip/{id}
  
  
  
#Cancelar um boleto
   
  ex do request - PUT
 
  URL :  http://localhost:8090/rest/bankslip/{id}
   
   ```
  {
  
  "status": "CANCELED"
  
  }
   ```
  
#Pagar um boleto
   
  ex do request - PUT
 
  URL :  http://localhost:8090/rest/bankslip/{id}
  
   
   ```
  {
  
  "status": "PAID"
  
  }
   ```
  
#Listar boletos  
 	
 	ex do request - GET
 	
   URL : http://localhost:8090/rest/bankslip
   
   
   
