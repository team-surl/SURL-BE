FROM eclipse-temurin:17-jre-focal

COPY ./build/libs/*.jar server.jar

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/server.jar"]
