# Etapa 1: Construção do JAR
FROM maven:3.9.8-amazoncorretto-21 AS build

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar os arquivos do projeto para o contêiner
COPY pom.xml .
COPY src ./src

# Compilar o projeto e gerar o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Execução do JAR
FROM openjdk:21-oracle

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o JAR gerado na etapa de construção para o contêiner
COPY --from=build /app/target/estetica-jar-with-dependencies.jar /app/estetica-jar-with-dependencies.jar

# Definir o ponto de entrada para executar o JAR
ENTRYPOINT ["java", "-jar", "/app/estetica-jar-with-dependencies.jar"]