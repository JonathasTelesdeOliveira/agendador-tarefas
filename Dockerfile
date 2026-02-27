FROM gradle:8.7-jdk AS build

WORKDIR /app

COPY . .

ENV JAVA_HOME=/opt/java/openjdk

RUN gradle build --no-daemon -x test

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar agendador-tarefas.jar

EXPOSE 8081

CMD ["java", "-jar", "agendador-tarefas.jar"]