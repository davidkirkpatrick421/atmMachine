# --- Build Stage ---
# Use an official Java (JDK) image to compile the code
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy all your project files into the container
COPY . .

# Compile your Java file(s)
# (This finds all .java files and compiles them)
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use a minimal Java (JRE) image to run the app
FROM openjdk:17-jre-slim-bullseye

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# The command to run when the container starts
# (Change 'ATMMachine' to your main class name)
CMD ["java", "ATMMachine"]