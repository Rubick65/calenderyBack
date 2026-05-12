# Etapa 1: Build
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar solo el pom.xml primero para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Ahora copiar el código fuente
COPY src ./src
RUN mvn clean package -DskipTests -q

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-XX:+OptimizeStringConcat", \
  "-jar", "app.jar"]