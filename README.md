API Pessoas
API REST desenvolvida com Spring Boot para gerenciamento de pessoas, com operações CRUD completas e banco de dados Oracle rodando em container Docker em uma VM na Azure.

🛠️ Tecnologias

Java 21
Spring Boot 4.x
Spring Data JPA / Hibernate
Oracle XE 21c (Docker)
Gradle
Microsoft Azure (VM)


⚙️ Pré-requisitos

Java 21+
Docker
Gradle (ou usar o wrapper ./gradlew)


🚀 Como executar
1. Clone o repositório
   bashgit clone <url-do-repositorio>
   cd cp2-devops
2. Suba o container Oracle
   bashdocker run -d \
   --name oracle-db \
   -p 1521:1521 \
   -e ORACLE_PASSWORD=oracle \
   gvenzl/oracle-xe:21-slim
   Aguarde a mensagem DATABASE IS READY TO USE! nos logs:
   bashdocker logs -f oracle-db
3. Configure o banco de dados
   Edite o arquivo src/main/resources/application.properties:
   propertiesspring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
   spring.datasource.username=system
   spring.datasource.password=oracle
   spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
4. Build e execução
   bash./gradlew clean build -x test --no-daemon
   java -jar build/libs/api-cp2-0.0.1-SNAPSHOT.jar
   A aplicação estará disponível em: http://localhost:8080

📋 Endpoints
Pessoas
MétodoEndpointDescriçãoGET/pessoasLista todas as pessoasGET/pessoas/{id}Busca pessoa por IDPOST/pessoasCria uma nova pessoaPUT/pessoas/{id}Atualiza uma pessoaDELETE/pessoas/{id}Remove uma pessoa
Exemplo de payload (POST/PUT)
json{
"nome": "João Silva",
"email": "joao@email.com",
"idade": 30
}

☁️ Infraestrutura Azure
A aplicação está hospedada em uma Virtual Machine (VM) na Azure com as seguintes configurações:

Sistema Operacional: Ubuntu 24
Banco de dados: Oracle XE 21c via container Docker
Porta exposta: 8080 (liberada via Network Security Group)

Para acessar a API remotamente:
http://<IP-PUBLICO-DA-VM>:8080/pessoas

🐳 Comandos Docker úteis
bash# Ver containers rodando
docker ps

# Ver logs do Oracle
docker logs -f oracle-db

# Parar o container
docker stop oracle-db

# Iniciar novamente
docker start oracle-db

📝 Observações

O banco de dados não persiste dados se o container for removido. Para persistência, configure um volume Docker.
Em VMs com pouca memória (free tier), recomenda-se rodar o Gradle com --no-daemon e limitar o heap da JVM.