# --- STAGE 1: Build (Using Official Maven Image) ---
# We use a 'maven' image so we don't need the wrapper files
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

# 1. Copy POM only to cache dependencies
COPY pom.xml .

# 2. Download dependencies (Standard Maven command)
RUN mvn dependency:go-offline

# 3. Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# --- STAGE 2: Run (Production Image) ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# 1. Copy the built JAR from Stage 1
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]