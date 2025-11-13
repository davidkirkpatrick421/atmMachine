# --- Build Stage ---
# Use the Eclipse Temurin JDK (full development kit)
FROM eclipse-temurin:17-jre as builder

WORKDIR /app

# Copy all your project files into the container
COPY . .

# Compile your Java file(s)
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the minimal Eclipse Temurin JRE (runtime only)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# Run your main class (change ATMMachine if needed)
CMD ["java", "-cp", "src", "atmMachine.ATMMachine"]