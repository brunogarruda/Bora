
FROM openjdk:8-jdk-alpine
VOLUME /gateway
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/gateway.jar gateway.jar
RUN sh -c 'touch gateway.jar'
EXPOSE 8080
#ENTRYPOINT exec java $JAVA_OPTS -jar gateway.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar gateway.jar
