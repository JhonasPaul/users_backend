# Imagen base con JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiar archivos necesarios de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Copiar el código fuente
COPY src ./src

# Descargar dependencias (mejora tiempo de build en futuros cambios)
RUN ./mvnw dependency:go-offline

# Compilar el proyecto sin tests
RUN ./mvnw clean package -DskipTests

# Expone el puerto de la app
EXPOSE 8080

# Ejecuta el JAR generado
ENTRYPOINT ["java", "-jar", "target/users_backend-0.0.1-SNAPSHOT.jar"]
