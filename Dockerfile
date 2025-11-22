# --- STAGE 1: Build the Application ---
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# 1. Copy Maven wrapper first to cache dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# 2. Download dependencies (This layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline

# 3. Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# --- STAGE 2: Run the Application (Production Image) ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# 1. Copy only the built JAR from Stage 1
COPY --from=builder /app/target/*.jar app.jar

# 2. Expose the port
EXPOSE 9090

# 3. Command to run
ENTRYPOINT ["java", "-jar", "app.jar"]