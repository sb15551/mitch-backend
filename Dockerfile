FROM maven:3.9.0-amazoncorretto-19 as maven
WORKDIR /app
COPY ./pom.xml .
COPY ./checkstyle.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn package
WORKDIR /app/target/dependency
RUN jar -xf ../mitch-1.0.0.jar

FROM eclipse-temurin:19.0.2_7-jre
ARG DEPENDENCY=/app/target/dependency
COPY --from=maven ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=maven ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=maven ${DEPENDENCY}/BOOT-INF/classes /app
CMD java -XX:MaxRAMPercentage=40.0 -cp app:app/lib/* ru.mitch.MitchApplication