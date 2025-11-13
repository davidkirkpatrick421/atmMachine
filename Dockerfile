# --- Build Stage ---
# Use the official, full JDK (Java Development Kit) for compiling
# This tag (jdk-bullseye) is verified and correct.
FROM openjdk:17-jdk-bullseye as builder

WORKDIR /app

# Copy all your project files into the container
COPY . .

# Compile your Java file(s)
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the minimal JRE (Java Runtime Environment) for running
# This tag (jre-slim-bullseye) is verified and correct.
FROM openjdk:17-jre-slim-bullseye

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# The command to run when the container starts
# (Change 'ATMMachine' to your main class name)
CMD ["java", "ATMMachine"]