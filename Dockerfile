# --- Build Stage ---
# Use the Official OpenJDK (JDK) image. This guarantees 'javac' is available.
FROM openjdk:17-jdk-bullseye as builder

WORKDIR /app

# Copy all your project files into the container
COPY . .

# Compile your Java file(s) (this will now find javac)
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the Official OpenJDK JRE (Runtime) image.
FROM openjdk:17-jre-slim

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# Run your main class (this is the final working command)
CMD ["java", "-cp", "src", "atmMachine.ATMMachine"]