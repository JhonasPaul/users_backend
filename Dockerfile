# Etapa 1: Construcción del JAR
FROM maven:3.8.8-amazoncorretto-17 AS builder

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app
RUN mvn dependency:go-offline -B

# Copiar solo los archivos necesarios
COPY mvnw ./
COPY .mvn/ .mvn
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen base para desarrollo (con recarga en caliente)
FROM openjdk:17-jdk-slim AS dev
WORKDIR /app

# Instalar netcat y Maven para wait-for-it.sh
RUN apt-get update && apt-get install -y netcat maven && rm -rf /var/lib/apt/lists/*


# Copiar el código fuente para permitir hot reload (sin JAR)
COPY . .

# Copiar el script wait-for-it.sh
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Configurar el puerto expuesto
EXPOSE 8001

# Aquí se usa Maven para iniciar la aplicación en lugar de buscar un JAR
CMD ["sh", "-c", "/wait-for-it.sh db 3306 -- ./mvnw spring-boot:run -Dspring-boot.run.profiles"]

## Etapa 2: Imagen final
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#
## Instalar nc (Netcat) para que wait-for-it.sh funcione
#RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*
#
## Copiar el JAR desde la etapa de construcción
#COPY --from=builder /app/target/users_backend-0.0.1-SNAPSHOT.jar .
#
## Copiar el script wait-for-it.sh
#COPY wait-for-it.sh /wait-for-it.sh
#RUN chmod +x /wait-for-it.sh
#
## Configurar la aplicación
#EXPOSE 8080
#ENTRYPOINT ["sh", "/wait-for-it.sh", "db", "3306", "java", "-jar", "users_backend-0.0.1-SNAPSHOT.jar"]
