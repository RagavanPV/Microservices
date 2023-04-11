FROM openjdk:17-alpine

WORKDIR /auth-server
EXPOSE 80
COPY ./build/libs/auth-server-0.0.1-SNAPSHOT.jar auth-server.jar
COPY ./entrypoint.sh .

RUN ["chmod", "+x", "./entrypoint.sh"]
ENTRYPOINT ["sh", "./entrypoint.sh"]