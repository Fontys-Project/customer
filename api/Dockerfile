FROM openjdk:14

RUN mkdir -p /opt/fontys/customer
WORKDIR /opt/fontys/customer
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar api.jar" ]
EXPOSE 8080

COPY ./src/main/resources/application.properties /opt/fontys/customer/application.properties
COPY ./target/api-0.1.0-INDEV.jar /opt/fontys/customer/api.jar

RUN touch api.jar
