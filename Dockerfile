FROM maven:3.9.6-amazoncorretto-21

WORKDIR /app

COPY . .

WORKDIR /app/v1

CMD [ "mvn", "tomcat7:run" ]