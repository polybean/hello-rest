FROM openjdk:8u191-jre-alpine3.9
ARG jar_file
WORKDIR /app
EXPOSE 8080
COPY target/$jar_file /app/
ENV jar_file=$jar_file
CMD java -jar $jar_file
