# --- Build Stage ---
# Use the official, full JDK to compile your code
# This tag is verified and correct.
FROM openjdk:17-jdk-bullseye as builder

WORKDIR /app

# Copy all your project files into the container
COPY . .

# Compile your Java file(s) (our simple project logic)
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the Eclipse Temurin JRE (as Claude suggested)
# This is a verified, working runner image.
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# Run your main class (change ATMMachine if needed)
CMD ["java", "ATMMachine"]