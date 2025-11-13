# --- Build Stage ---
# Use the Eclipse Temurin JDK (full development kit)
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app
COPY . .
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the minimal Eclipse Temurin JRE (runtime only)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /app .
CMD ["java", "ATMMachine"]