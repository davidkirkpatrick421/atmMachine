# --- Build Stage ---
# Use the Official OpenJDK 17 JDK (This is the most reliable tag)
FROM openjdk:17 as builder

WORKDIR /app

# Copy all your project files into the container
COPY . .

# Compile your Java file(s)
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the Official OpenJDK 17 JRE (Runtime)
FROM openjdk:17-jre

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# Run your main class
CMD ["java", "-cp", "src", "atmMachine.ATMMachine"]