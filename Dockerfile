# --- Build Stage ---
# Use the Microsoft/Eclipse Temurin JDK. This uses a different server than Docker Hub.
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as builder

WORKDIR /app
COPY . .
RUN find . -name "*.java" | xargs javac

# --- Run Stage ---
# Use the minimal Microsoft/Eclipse Temurin JRE (Runtime)
FROM mcr.microsoft.com/openjdk/jre:17-ubuntu

WORKDIR /app
COPY --from=builder /app .
CMD ["java", "-cp", "src", "atmMachine.ATMMachine"]