# --- Build Stage ---
# Use the official Eclipse Temurin JDK for building. This is the most stable source.
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app
COPY .. .
# Compile your Java file(s)
# RUN find . -name "*.java" | xargs javac
# This command finds only main app .java files to ignore JUnit test for now
RUN find src -name "*.java" | xargs javac

# --- Run Stage ---
# Use the Official Eclipse Temurin JRE for running.
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy *only* the compiled .class files from the builder stage
COPY --from=builder /app .

# Run your main class (This is the final working command)
CMD ["java", "-cp", "src", "atmMachine.ATMSystem"]